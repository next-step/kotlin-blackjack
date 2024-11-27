package view

import BlackJackGame
import CardNumber
import CardSuit
import DrawCard
import DrawResult
import Player
import PlayerName

class InputView {
    fun inputBlackJack() {
        val players = inputPlayerNames()
        val blackJackGame = BlackJackGame(players)

        val initialDrawResult = blackJackGame.initialDraw()
        printDrawResult(initialDrawResult)

        additionalDraw(blackJackGame)
    }

    private fun inputPlayerNames(): List<Player> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val inputPlayerNames = readlnOrNull()
        return inputPlayerNames
                ?.split(",")
                ?.map { Player(PlayerName(it)) }
                ?: throw IllegalArgumentException("게임에 참여할 사람의 이름은 필수 입니다.")
    }

    private fun printDrawResult(inititalDrawResult: List<DrawResult>) {
        inititalDrawResult.forEach {
            val displayCards = it.cards
                .map { drawCard -> convertDisplayCardString(drawCard) }
                .joinToString(", ")

            println("${it.playerName}카드: $displayCards")
        }
    }

    private fun convertDisplayCardString(drawCard: DrawCard): String {
        val suit = convertSuit(drawCard.suit)
        val number = convertNumber(drawCard.number)
        return "$number$suit"
    }

    private fun convertSuit(suit: CardSuit): String =
        when(suit) {
            CardSuit.HEARTS -> "하트"
            CardSuit.DIAMONDS -> "다이아몬드"
            CardSuit.CLUBS -> "클로버"
            CardSuit.SPADES -> "스페이드"
        }

    private fun convertNumber(number: CardNumber): String =
        when(number) {
            CardNumber.ACE -> "A"
            CardNumber.TWO -> "2"
            CardNumber.THREE -> "3"
            CardNumber.FOUR -> "4"
            CardNumber.FIVE -> "5"
            CardNumber.SIX -> "6"
            CardNumber.SEVEN -> "7"
            CardNumber.EIGHT -> "8"
            CardNumber.NINE -> "9"
            CardNumber.TEN -> "10"
            CardNumber.JACK -> "J"
            CardNumber.QUEEN -> "Q"
            CardNumber.KING -> "K"
        }

    private fun additionalDraw(blackJackGame: BlackJackGame) {
        while (blackJackGame.canDrawForAllPlayers()) {
            val playerName = blackJackGame.findDrawPlayer()
            val needAdditionalDraw = inputNeedAdditionalDraw(playerName)

            if (needAdditionalDraw) {
                val drawCard = blackJackGame.drawCard(playerName)
                printDrawResult(listOf(drawCard))
            } else {
                blackJackGame.stopDraw(playerName)
            }
        }
    }

    private fun inputNeedAdditionalDraw(playerName: PlayerName): Boolean {
        println("${playerName}는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)")
        val input = readlnOrNull()
        return !(input.isNullOrBlank() || input == "n")
    }
}