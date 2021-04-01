package blackjack

fun main() {
    val game = BlackJackGame()
    val cardExtractor = RandomCardExtractor()
    val dealer = Dealer()
    val names = game.parseName(inputName())
    val prices = names.map { inputPrice(it).parseInt() }
    val players = game.parsePlayers(names, prices)
    val users = game.getUsers(dealer, players)

    users.firstDeal(cardExtractor)
    printFirstDeal(users)

    if (dealer.isBust()) {
        printDealerBust(dealer)
        return
    } else if (dealer.isBlackJack()) {
        printResult(users)
        return
    }

    players.players.forEach {
        game.hitOrStand(it, cardExtractor)
    }

    printResult(users)
}

class BlackJackGame {

    fun getUsers(dealer: Dealer, players: Players): Users {
        return Users(players.players + dealer)
    }

    fun parseName(names: String?): List<String> {
        require(names != null && names.isNotBlank()) { "이름을 입력해주세요" }

        return names.split(DELIMITER)
    }

    fun parsePlayers(names: List<String>, prices: List<Int>): Players {
        return Players((names.indices).map { Player(names[it], prices[it]) })
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
