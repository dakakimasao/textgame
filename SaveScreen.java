package com.mycompany.mytextgame;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**

 세이브 화면 - 저장된 게임 데이터가 있으면 불러오고, 없으면 새로운 캐릭터를 생성합니다.
 */
public class SaveScreen extends Panel {

    private Label infoLabel;
    private TextField nameField;
    private Button actionButton;

    public SaveScreen() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);


        // 세이브 파일 존재 여부 확인
        File saveFile = FileManager.getSaveFile();
        if (saveFile.exists()) {
            // 저장 파일이 있을 경우: 파일에서 데이터를 읽어옵니다.
            GameData data = FileManager.loadGameData();

            // 저장된 캐릭터 정보를 표시
            infoLabel = new Label("저장된 캐릭터: " + data.getName(), Label.CENTER);
            infoLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            add(infoLabel, gbc);

            // 계속하기 버튼 생성
            actionButton = new Button("계속하기");
            actionButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            add(actionButton, gbc);

            // 버튼 클릭 시 게임 진행 관련 로직 추가 가능 (예: 게임 화면 전환)
            actionButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // 여기서 게임 본 화면으로 전환하는 로직을 추가할 수 있습니다.
                    Dialog dialog = new Dialog(new Frame(), "알림", true);
                    dialog.setLayout(new FlowLayout());
                    Label msg = new Label("게임을 계속합니다!");
                    dialog.add(msg);
                    Button okBtn = new Button("확인");
                    okBtn.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            dialog.setVisible(false);
                        }
                    });
                    dialog.add(okBtn);
                    dialog.setSize(250, 150);
                    dialog.setVisible(true);
                }
            });
        } else {
            // 저장 파일이 없을 경우: 새로운 캐릭터 생성 폼 표시
            Label promptLabel = new Label("새 캐릭터 이름 입력:", Label.CENTER);
            promptLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            add(promptLabel, gbc);

            // 캐릭터 이름 입력 필드
            nameField = new TextField(20);
            gbc.gridy = 1;
            add(nameField, gbc);

            // 저장 버튼 생성
            actionButton = new Button("저장");
            actionButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            add(actionButton, gbc);

            // 저장 버튼 클릭 시 입력된 이름으로 GameData 객체를 생성 후 파일에 저장
            actionButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String charName = nameField.getText().trim();
                    if (!charName.isEmpty()) {
                        GameData newData = new GameData(charName);
                        // 파일에 저장
                        boolean success = FileManager.saveGameData(newData);
                        if (success) {
                            // 저장 성공 시 정보 업데이트 및 "계속하기" 버튼으로 변경
                            removeAll();
                            repaint();

                            infoLabel = new Label("저장된 캐릭터: " + newData.getName(), Label.CENTER);
                            infoLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
                            gbc.gridx = 0;
                            gbc.gridy = 0;
                            gbc.gridwidth = 2;
                            add(infoLabel, gbc);

                            actionButton = new Button("계속하기");
                            actionButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
                            gbc.gridy = 1;
                            gbc.gridwidth = 1;
                            add(actionButton, gbc);

                            actionButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    // 게임 계속 진행 로직 추가 가능
                                    Dialog dialog = new Dialog(new Frame(), "알림", true);
                                    dialog.setLayout(new FlowLayout());
                                    Label msg = new Label("게임을 계속합니다!");
                                    dialog.add(msg);
                                    Button okBtn = new Button("확인");
                                    okBtn.addActionListener(new ActionListener(){
                                        public void actionPerformed(ActionEvent e){
                                            dialog.setVisible(false);
                                        }
                                    });
                                    dialog.add(okBtn);
                                    dialog.setSize(250, 150);
                                    dialog.setVisible(true);
                                }
                            });
                            revalidate();
                        } else {
                            // 저장 실패 시 에러 다이얼로그 표시
                            Dialog dialog = new Dialog(new Frame(), "오류", true);
                            dialog.setLayout(new FlowLayout());
                            Label errorMsg = new Label("캐릭터 저장에 실패했습니다.");
                            dialog.add(errorMsg);
                            Button okBtn = new Button("확인");
                            okBtn.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e){
                                    dialog.setVisible(false);
                                }
                            });
                            dialog.add(okBtn);
                            dialog.setSize(250, 150);
                            dialog.setVisible(true);
                        }
                    }
                }
            });
        }
    }
}