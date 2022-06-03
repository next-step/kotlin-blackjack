package com.nextstep.jngcii.blackjack.view

object InputView {
    tailrec fun getNames(read: () -> String?): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val names = InputValidator.parseNames(read())
        if (names == null) {
            println("다시 입력해주세요.")
            return getNames(::readLine)
        }
        return names
    }
}
