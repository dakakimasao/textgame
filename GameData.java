package com.mycompany.mytextgame;

import java.io.Serializable;

/**

 게임 데이터 모델 클래스 - 캐릭터 이름 정보를 저장합니다.
 */
public class GameData implements Serializable {
    private String name;

    public GameData(String name) {
        this.name = name;
    }

    // 캐릭터의 이름을 반환하는 메서드
    public String getName() {
        return name;
    }

    // 데이터를 문자열 형태로 변환 (파일 저장 시 사용)
    @Override
    public String toString() {
        return name;
    }
}