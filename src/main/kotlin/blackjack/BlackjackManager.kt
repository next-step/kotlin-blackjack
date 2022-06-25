package blackjack

class BlackjackManager {
    lateinit var players: List<Player>
    lateinit var dealer: BlackjackDealer

    fun startGame(names: List<String>) {
        this.players = names.map { Player(name = it) }
        this.dealer = BlackjackDealer()
        dealer.shuffleCardDeck()
        dealer.startTheGame(players)
    }

    fun playGame() {
        players.forEach {
            while (it.isPlaying()) {
                println("${it.name}는(은) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
                val answer = readln()
                playTurn(it, Answer.of(answer))
            }
        }
    }

    private fun playTurn(player: Player, answer: Answer) {
        if (answer.isHit()) {
            player.hit()
            dealer.sendCard(player)
            println("${player.name}카드: ${player.handToList().joinToString { card -> card.number.symbol + card.suit.korName }}")
            return
        }
        player.stay()
    }

    fun result() {
        players.forEach { player ->
            outputResult(player)
        }
    }

    private fun outputResult(player: Player) {
        println(
            "${player.name}카드: ${player.handToList().joinToString { card -> card.number.symbol + card.suit.korName }} - 결과: ${player.totalScoreInHand().value}"
        )
    }
}
