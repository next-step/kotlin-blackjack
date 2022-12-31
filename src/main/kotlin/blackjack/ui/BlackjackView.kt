package blackjack.ui

import blackjack.domain.Card
import blackjack.domain.Gamer
import blackjack.domain.Gamers

object BlackjackView {

    private const val DELIMITER = ","
    private const val INPUT_PLAYER_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val INPUT_CARD_SIGN = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"

    private const val OUTPUT_GAMER_MESSAGE = "에게 2장의 카드를 나누었습니다."

    private const val MAX_SCORE = 21
    private const val SIGN_NO = "n"

    fun printStartGamer(gamers: Gamers) {
        println("${gamers.value.map { it.name }}$OUTPUT_GAMER_MESSAGE")

        gamers.value.forEach {
            println(it.toString())
        }
    }

    fun inputGamers(): Gamers {
        println(INPUT_PLAYER_MESSAGE)
        return Gamers(
            readLine()!!.split(DELIMITER)
                .map { Gamer(it) }
        )
    }

    fun inputCard(inputGamers: Gamers): Gamers {
        var gamers = Gamers()

        inputGamers.value.forEach { gamer ->
            gamers += game(gamer)
        }

        return gamers
    }

    private fun game(gamer: Gamer): Gamer {
        if (gamer.isFinish) {
            return gamer
        }

        println("${gamer.name}$INPUT_CARD_SIGN")
        val sign = readLine()!!

        return game(inputCardSignGamer(gamer, sign))
    }

    private fun inputCardSignGamer(gamer: Gamer, sign: String): Gamer {
        if (gamer.isFinish) {
            return gamer
        }

        if (sign == SIGN_NO) {
            return gamer.copy(isFinish = true)
        }

        val cards = gamer.cards.plus(Card.of())

        return gamer.copy(
            cards = gamer.cards.plus(Card.of()),
            isFinish = cards.calculateScore() >= MAX_SCORE
        )
            .also { println(it.toString()) }
    }

    fun printResult(gamers: Gamers) {
        gamers.value.forEach {
            println("$it - 결과 : ${it.cards.calculateScore()}")
        }
    }
}
