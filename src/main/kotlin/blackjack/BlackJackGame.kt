package blackjack

fun main() {
    val game = BlackJackGame()
    val cardExtractor = RandomCardExtractor()
    val dealer = Dealer()
    val players = game.parsePlayers(inputName())
    val users = game.getUsers(dealer, players)

    users.firstDeal(cardExtractor)
    printFirstDeal(users)

    if (dealer.isBust()) {
        printDealerBust(dealer)
        return
    }

    players.players.forEach {
        game.hitOrStand(it, cardExtractor)
    }

    printResult(dealer, players)
}

class BlackJackGame {

    fun getUsers(dealer: Dealer, players: Players): Users {
        return Users(players.players + dealer)
    }

    fun parsePlayers(names: String?): Players {
        require(names != null) { "이름을 입력해주세요" }

        return Players(names.split(DELIMITER).map { Player(it, 1) })
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

    fun hitOrStand(player: Player, cardExtractor: CardExtractor) {
        while (!player.isBust()) {
            val answer = getReceiveCardAnswer(player)
            if (answer == YES) {
                player.cardDeck.add(cardExtractor.getCard())
            }
            println(player.cardText())

            if (answer == NO) {
                break
            }
        }
    }

    companion object {
        const val YES = "y"
        const val NO = "n"
        const val DELIMITER = ","
    }
}
