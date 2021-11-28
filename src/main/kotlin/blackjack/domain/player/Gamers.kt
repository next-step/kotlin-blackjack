package blackjack.domain.player

import blackjack.domain.card.Deck
import blackjack.domain.game.BlackJackResult

private const val GAME_START_HIT_COUNT = 2

class Gamers(private val players: List<Player>, private val dealer: Dealer) {

    val gamers: List<Gamer> = players + dealer

    fun hitAtGameStart(deck: Deck) {
        repeat(GAME_START_HIT_COUNT) {
            gamers.forEach { it.hit(deck) }
        }
    }

    fun hitWhileWant(deck: Deck, answerProvider: AnswerProvider) {
        gamers.forEach {
            it.hitWhileWant(deck, answerProvider)
        }
    }

    fun getResult(): BlackJackResult {
        return BlackJackResult.from(dealer, players)
    }

    companion object {

        fun from(players: List<Player>, dealerAfterHit: AfterHitWhileCallback): Gamers {
            val dealer = Dealer(afterHitCallBack = dealerAfterHit)
            return Gamers(players, dealer)
        }
    }
}
