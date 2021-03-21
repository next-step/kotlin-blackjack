package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardSuit
import blackjack.domain.card.CardSymbol
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class GameTableTest {
    private lateinit var gameTable: GameTable

    @BeforeEach
    fun setUp() {
        val players = createPlayers("pobi")
        gameTable = GameTable(players, CardDeck(SortedShuffleStrategy()))
    }

    @DisplayName("첫 번째 라운드에 2장씩 카드를 뽑은 UserInfo 반환")
    @Test
    fun proceedFirstRound() {
        val userInfo = gameTable.proceedFirstRound()
        val dealerCards = listOf(
            createCard(CardSymbol.KING.name, CardSuit.SPADE.name),
            createCard(CardSymbol.KING.name, CardSuit.HEART.name)
        )
        val playerCards = listOf(
            createCard(CardSymbol.KING.name, CardSuit.DIAMOND.name),
            createCard(CardSymbol.KING.name, CardSuit.CLUB.name)
        )

        assertAll(
            { assertThat(userInfo.dealer.hands.cards).isEqualTo(dealerCards) },
            { assertThat(userInfo.players.players[0].hands.cards).isEqualTo(playerCards) },
        )
    }
}