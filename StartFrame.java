package com.mycompany.mytextgame;

import java.awt.*;
import java.awt.event.*;

/**

 게임 시작 화면을 구성하는 클래스 (타이틀과 시작 버튼 포함)
 */
public class StartFrame extends Panel {

    private GameFrame parentFrame;

    public StartFrame(GameFrame frame) {
        this.parentFrame = frame;
        setLayout(new BorderLayout());


        // 게임 제목 레이블
        Label titleLabel = new Label("My Text Game", Label.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        add(titleLabel, BorderLayout.CENTER);

        // 시작 버튼 생성
        Button startButton = new Button("Start");
        startButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        add(startButton, BorderLayout.SOUTH);

        // 시작 버튼 클릭 시 SaveScreen으로 전환
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parentFrame.showSaveScreen();
            }
        });

        // 패널 배경색 설정
        setBackground(Color.LIGHT_GRAY);
    }
}