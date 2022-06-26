package camp.nextstep.blackjack.game

import camp.nextstep.blackjack.card.CardDeck
import camp.nextstep.blackjack.card.CardShuffler
import camp.nextstep.blackjack.player.Dealer
import camp.nextstep.blackjack.player.Gambler
import camp.nextstep.blackjack.player.Player

class BlackJackGame private constructor(private var _cardDeck: CardDeck, private val _participants: List<Gambler>) {

    val gamblerTurns: List<Turn<Gambler>>

    val dealer = Dealer()

    val dealerTurn: Turn<Dealer> = Turn(dealer)

    val cardDeck get() = CardDeck.of(_cardDeck.cards)

    val participants get() = _participants.toList()

    init {
        _cardDeck = CardShuffler.shuffle(_cardDeck)

        repeat(INIT_CARD_NUMBER) {
            dealer.serve(_cardDeck, _participants + dealer)
        }

        gamblerTurns = _participants.map { Turn(it) }
    }

    fun result(): GameResult {
        val isEnded = gamblerTurns.all { it.isDone } && dealerTurn.isDone
        check(isEnded) { "게임이 종료되지 않았습니다." }

        return GameResult(
            _participants.map { GamblerScore(it, Score.of(it.hand)) }
        )
    }

    fun play(turn: Turn<out Player>, actionProducer: (Player) -> Action, actionCallback: (Action) -> Unit = {}) {
        while (!turn.isDone) {
            val action = actionProducer(turn.player)
            turn.play(action)
            actionCallback(action)
        }
    }

    fun dealersPlay(turn: Turn<Dealer>, actionCallback: (Action) -> Unit = {}) {
        dealer.openCards()
        play(turn, { DealerActionProducer.produce(dealer) }, actionCallback)
    }

    inner class Turn<P : Player>(val player: P) {

        var isDone = false
            internal set

        internal fun play(action: Action) {
            check(!isDone) { "턴이 종료되었습니다." }

            if (action == Action.HIT) {
                dealer.serve(_cardDeck, player)
            }

            val gamblerScore = Score.of(player.hand)
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
