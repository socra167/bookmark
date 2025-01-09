package com.socra.bookmark.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.datatransfer.*;
import java.net.URI;
import java.time.LocalDate;

import com.socra.bookmark.domain.Bookmark;
import com.socra.bookmark.domain.MetaData;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClipboardMonitorService {
    private final BookmarkService bookmarkService;
    private String lastClipboardContent = ""; // 이전 클립보드 값을 저장할 변수
    private Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // @Scheduled(fixedRate = 1000) // 1초마다 클립보드 확인
    public void monitorClipboard() {
        try {
            Transferable content = clipboard.getContents(null);
            if (content != null && content.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                String text = (String) content.getTransferData(DataFlavor.stringFlavor);

                // 클립보드 값이 이전과 다를 경우에만 처리
                if (!text.equals(lastClipboardContent) &&
                        text.startsWith("http") &&
                        !bookmarkService.existsByUri(new URI(text))
                ) {
                    logger.debug("Detected URL: {}", text);
                    URI uri = new URI(text);
                    String title = "";
                    String description = "";
                    try {
                        MetaData metaData = MetaData.MetaDataExtractor.getMetaDataFromUri(uri);
                        title = metaData.getTitle();
                        description = metaData.getDescription();
                    } catch (Exception e) {
                        logger.debug("HTTP error fetching URL: {}", text);
                    }
                    Bookmark bookmark = Bookmark.builder()
                            .name(title)
                            .uri(uri)
                            .description(description)
                            .date(LocalDate.now())
                            .build();
                    bookmarkService.saveBookmark(bookmark); // URL을 DB에 저장
                    lastClipboardContent = text; // 새로운 클립보드 값을 저장
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
