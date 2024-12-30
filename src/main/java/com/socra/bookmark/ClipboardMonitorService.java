package com.socra.bookmark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.datatransfer.*;
import java.net.URI;
import java.time.LocalDate;

@Service
public class ClipboardMonitorService {

    @Autowired
    private BookmarkService bookmarkService;

    private Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    private String lastClipboardContent = ""; // 이전 클립보드 값을 저장할 변수

    @Scheduled(fixedRate = 1000) // 1초마다 클립보드 확인
    public void monitorClipboard() {
        try {
            Transferable content = clipboard.getContents(null);
            if (content != null && content.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                String text = (String) content.getTransferData(DataFlavor.stringFlavor);

                // 클립보드 값이 이전과 다를 경우에만 처리
                if (!text.equals(lastClipboardContent)) {
                    if (text.startsWith("http")) {
                        System.out.println("Detected URL: " + text);
                        Bookmark bookmark = Bookmark.builder()
                                .uri(new URI(text))
                                .name("test")
                                .date(LocalDate.now())
                                .build();
                        bookmarkService.saveBookmark(bookmark); // URL을 DB에 저장
                        lastClipboardContent = text; // 새로운 클립보드 값을 저장
                        Toolkit.getDefaultToolkit().beep();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
