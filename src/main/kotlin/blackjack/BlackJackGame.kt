package blackjack

fun main() {
    val game = BlackJackGame()
    val cardExtractor = RandomCardExtractor()
    val players = game.getUsers(inputName())

    players.hit(cardExtractor)
    printHit(players)

    players.users.filterIsInstance(Player::class.java).forEach {
        game.moreCard(it, cardExtractor)
    }

    printResult(players)
}

class BlackJackGame {

    fun getUsers(names: String?): Users {
        return Users(parsePlayers(names) + Dealer())
    }

    private fun parsePlayers(names: String?): List<Player> {
        require(names != null) { "이름을 입력해주세요" }

        return names.split(DELIMITER).map { Player(it) }
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

    fun moreCard(player: Player, randomCardExtractor: RandomCardExtractor) {
        while (!player.isDead()) {
            val answer = getReceiveCardAnswer(player)
            if (answer == YES) {
                player.cardDeck.add(randomCardExtractor.getCard())
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
