package blackjack

import blackjack.BlackJack.Companion.SEPARATOR

fun main() {
    val blackJack = BlackJack()
    val cardExtractor = CardExtractor()
    val players = blackJack.parsePlayers(inputName())

    players.addCardAllPlayer(cardExtractor)
    players.addCardAllPlayer(cardExtractor)

    println(players.players.joinToString(SEPARATOR) { it.name } + "에게 2장의 카드를 나누어주었습니다.")
    players.players.forEach {
        println(getPlayerCardText(it))
    }

    players.players.forEach {
        blackJack.moreCard(it, cardExtractor)
    }

    players.players.forEach {
        print(getPlayerCardText(it))
        println(" - 결과: ${it.cardDeck.getScore()}")
    }
}

class BlackJack {

    fun parsePlayers(names: String?): Players {
        require(names != null) { "이름을 입력해주세요" }

        return Players(names.split(DELIMITER).map { Player(it) })
    }

    private fun getReceiveCardAnswer(player: Player): String {
        while (true) {
            val answer = inputReceiveCardAnswer(player.name)
            require(answer != null) { "응답을 반드시 입력해주세요." }

            if (answer != YES && answer != NO) {
                println("응답은 y나 n을 해주세요.")
            } else {
                return answer
            }
        }
    }

    fun moreCard(player: Player, cardExtractor: CardExtractor) {
        while (!player.isDead()) {
            val answer = getReceiveCardAnswer(player)
            if (answer == YES) {
                player.cardDeck.add(cardExtractor.getCard())
            } else {
                println(getPlayerCardText(player))
                break
            }
            println(getPlayerCardText(player))
        }
    }

    companion object {
        const val YES = "y"
        const val NO = "n"
        const val SEPARATOR = ", "
        const val DELIMITER = ","
    }
}
