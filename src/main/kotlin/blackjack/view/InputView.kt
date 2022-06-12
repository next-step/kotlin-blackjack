package blackjack.view

import blackjack.domain.Card
import blackjack.domain.GameRule.INIT_DRAW_CARD_COUNT
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.asText
import blackjack.domain.toPlayers
import java.util.Locale

object InputView {
    private const val PLAYER_DELIMITER = ","
    private const val MINIMUM_PLAYER_COUNT = 2
    private const val ILLEGAL_ANSWER_MSG = "y, n만 사용하실 수 있습니다."

    enum class Answer {
        Y, N;

        fun isApprove() = this == Y

        companion object {
            fun of(answer: String): Answer {
                return Answer.values().firstOrNull { answer.uppercase(Locale.getDefault()) == it.name }
                    ?: throw java.lang.IllegalArgumentException(ILLEGAL_ANSWER_MSG)
            }
        }
    }

    fun getPlayer(): Players {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val players = readln().split(PLAYER_DELIMITER)

        require(players.size >= MINIMUM_PLAYER_COUNT) {
            "반드시 $MINIMUM_PLAYER_COUNT 이상의 참가자가 필요합니다."
        }

        return players.toPlayers()
    }

    fun initDistributeCard(deckFunc: () -> Card): List<Card> {
        return List(INIT_DRAW_CARD_COUNT) {
            deckFunc()
        }
    }

    fun printCurrentCard(player: Player) {
        println("${player.name}카드: ${player.cards.asText()}")
    }

    fun askGetCard(name: String, deckFunc: () -> Card): List<Card> {
        val list: MutableList<Card> = mutableListOf()

        do {
            println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
            val answer = Answer.of(readln())

            if (answer.isApprove()) {
                deckFunc().apply {
                    println("${name}카드 : ${this.fullName}")
                    list.add(this)
                }
            }
        } while (answer.isApprove())

        return list
    }
}
