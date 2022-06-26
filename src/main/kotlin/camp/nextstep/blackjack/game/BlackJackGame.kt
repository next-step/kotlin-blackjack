package camp.nextstep.blackjack.game

import camp.nextstep.blackjack.card.CardDeck
import camp.nextstep.blackjack.card.CardShuffler
import camp.nextstep.blackjack.player.Dealer
import camp.nextstep.blackjack.player.Gambler

class BlackJackGame private constructor(private var _cardDeck: CardDeck, private val _participants: List<Gambler>) {

    val turns: List<Turn>

    val dealer = Dealer()

    val cardDeck get() = CardDeck.of(_cardDeck.cards)

    val participants get() = _participants.toList()

    init {
        _cardDeck = CardShuffler.shuffle(_cardDeck)

        repeat(INIT_CARD_NUMBER) {
            dealer.serve(_cardDeck, _participants + dealer)
        }

        turns = _participants.map { Turn(it) }
    }

    fun result(): GameResult {
        val isEnded = turns.all { it.isDone }
        check(isEnded) { "게임이 종료되지 않았습니다." }

        return GameResult(
            _participants.map { GamblerScore(it, Score.of(it.hand)) }
        )
    }

    fun play(turn: Turn, actionProducer: (Gambler) -> Action, afterAction: () -> Unit = {}) {
        while (!turn.isDone) {
            val action = actionProducer(turn.gambler)
            turn.play(action)
            afterAction()
        }
    }

    inner class Turn(val gambler: Gambler) {

        var isDone = false
            internal set

        internal fun play(action: Action) {
            check(!isDone) { "턴이 종료되었습니다." }

            if (action == Action.HIT) {
                dealer.serve(_cardDeck, gambler)
            }

            val gamblerScore = Score.of(gambler.hand)
            if (gamblerScore.isBust() || action == Action.STAY) {
                isDone = true
            }
        }
    }

    companion object {
        const val INIT_CARD_NUMBER = 2

        fun new(participants: List<Gambler> = listOf()): BlackJackGame {
            return BlackJackGame(CardDeck.new(), participants)
        }
    }
}
