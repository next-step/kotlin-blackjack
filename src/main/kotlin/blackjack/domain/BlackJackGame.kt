package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.holder.Dealer
import blackjack.domain.holder.Player
import blackjack.view.dto.AddCardResult
import blackjack.view.dto.InitResult
import blackjack.view.dto.PlayerGameResult

private const val INITIAL_CARD_COUNT = 2

class BlackJackGame(
    private val players: List<Player>,
    private val dealer: Dealer = Dealer(),
    private val deck: CardDeck = CardDeck(),
) {
    fun start(): List<InitResult> = dealerInit() + playerInit()

    private fun dealerInit(): List<InitResult> {
        dealer.addCard(deck.deal(INITIAL_CARD_COUNT))
        return listOf(InitResult(dealer, dealer.firstCard()))
    }

    private fun playerInit(): List<InitResult> = players.map {
        val player = it.firstTurn(deck.deal(INITIAL_CARD_COUNT))
        InitResult(player)
    }

    fun addCard(name: String): AddCardResult = getPlayerBy(name)
        .addCard(deck.deal())
        .let { AddCardResult(it) }

    private fun getPlayerBy(name: String): Player {
        return players.firstOrNull { it.name == name } ?: throw IllegalArgumentException("존재하지 않는 플레이어 이름입니다.")
    }

    fun hitDealer(): Int {
        return dealer.hitUntil(deck)
    }

    fun result(): List<PlayerGameResult> {
        return dealer.result(players)

    }
}
