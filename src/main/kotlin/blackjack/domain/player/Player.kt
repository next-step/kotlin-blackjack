package blackjack.domain.player

import blackjack.constant.ErrorMessages
import blackjack.domain.InputInterface
import blackjack.domain.Money
import blackjack.domain.OutputInterface
import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Deck

/**
 * 게임에 참여하는 플레이어의 추상화 클래스
 * Created by Jaesungchi on 2022.07.02..
 */
abstract class Player(val name: String, initCards: List<Card>) {
    private val _cards: Cards = Cards(initCards)
    val cards: Cards
        get() = _cards

    var income = Money()
        private set

    fun addIncome(money: Money) {
        income += money
    }

    init {
        require(name.isNotEmpty()) { ErrorMessages.NAME_IS_EMPTY }
    }

    fun hit(card: Card) {
        _cards.addCard(card)
    }

    protected fun isBust(): Boolean {
        return _cards.getScore().isBust()
    }

    private fun isBlackJack(): Boolean {
        return _cards.isBlackJack()
    }

    protected fun match(player: Player): Match {
        return when {
            isBust() && player.isBust() -> Match.DRAW
            player.isBust() -> Match.WIN
            isBust() -> Match.LOSE
            isBlackJack() && player.isBlackJack() -> Match.DRAW
            player.isBlackJack() -> Match.LOSE_BLACKJACK
            isBlackJack() -> Match.WIN_BLACKJACK
            player._cards.getScore() < _cards.getScore() -> Match.WIN
            player._cards.getScore() > _cards.getScore() -> Match.LOSE
            else -> Match.DRAW
        }
    }

    protected abstract fun isReadyToHit(input: InputInterface, output: OutputInterface): Boolean
    protected abstract fun afterHit(output: OutputInterface)

    fun hitStage(deck: Deck, input: InputInterface, output: OutputInterface) {
        while (isReadyToHit(input, output)) {
            hit(deck.takeCard())
            afterHit(output)
        }
    }

    companion object {
        const val BLACKJACK_WIN_PROFIT_MARGIN = 1.5
    }
}

enum class Match {
    WIN, LOSE, DRAW, WIN_BLACKJACK, LOSE_BLACKJACK;

    fun earningMoney(money: Money): Money {
        return when (this) {
            WIN -> money
            WIN_BLACKJACK -> money.times(Player.BLACKJACK_WIN_PROFIT_MARGIN)
            LOSE, LOSE_BLACKJACK -> -money
            else -> ZERO_MONEY
        }
    }

    companion object {
        private val ZERO_MONEY = Money()
    }
}
