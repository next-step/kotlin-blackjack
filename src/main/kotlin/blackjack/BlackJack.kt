package blackjack

fun main() {
    val blackJack = BlackJack()
    val cardExtractor = CardExtractor()
    val players = blackJack.parsePlayers(inputName())

    players.players.forEach {
        it.cardDeck.add(cardExtractor.getCard())
        it.cardDeck.add(cardExtractor.getCard())
    }
    println(players.players.joinToString(", ") { it.name } + "에게 2장의 카드를 나누어주었습니다.")
    players.players.forEach {
        println(it.getCardText())
    }

    players.players.forEach {
        blackJack.moreCard(it, cardExtractor)
    }

    players.players.forEach {
        print(it.getCardText())
        println(" - 결과: ${it.cardDeck.getScore()}")
    }
}

class BlackJack {

    fun parsePlayers(names: String?): Players {
        require(names != null) { "이름을 입력해주세요" }

        return Players(
            names.split(",").map {
                require(it.isNotBlank()) { "이름이 공백이어서는 안됩니다." }
                Player(it)
            }
        )
    }

    fun parseAnswer(answer: String?): Boolean {
        require(answer != null && (answer == "y" || answer == "n")) { "응답은 y나 n을 해주세요." }
        return answer == "y"
    }

    fun moreCard(player: Player, cardExtractor: CardExtractor) {
        while (!player.isDead()) {
            val answer = inputReceiveCardAnswer(player.name)
            if (parseAnswer(answer)) {
                player.cardDeck.add(cardExtractor.getCard())
            } else {
                println(player.getCardText())
                break
            }
            println(player.getCardText())
        }
    }
}
