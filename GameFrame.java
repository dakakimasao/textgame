package com.mycompany.mytextgame;

import java.awt.*;
import java.awt.event.*;

/**

 게임의 메인 프레임을 정의하는 클래스 (AWT Frame을 상속)
 */
public class GameFrame extends Frame {

    private Panel currentPanel; // 현재 보여지는 패널

    public GameFrame() {
// 창 설정
        setTitle("My Text Game");
        setSize(400, 300);
        setLayout(new BorderLayout());
        setResizable(false);


        // 창 닫기 이벤트 처리
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        // 시작 화면 표시
        showStartScreen();
        setVisible(true);
    }

    // 시작 화면 패널을 설정하는 메서드
    public void showStartScreen() {
        if (currentPanel != null) {
            remove(currentPanel);
        }
        currentPanel = new StartFrame(this);
        add(currentPanel, BorderLayout.CENTER);
        validate();
        repaint();
    }

    // 저장 화면 패널을 설정하는 메서드
    public void showSaveScreen() {
        if (currentPanel != null) {
            remove(currentPanel);
        }
        currentPanel = new SaveScreen();
        add(currentPanel, BorderLayout.CENTER);
        validate();
        repaint();
    }
}