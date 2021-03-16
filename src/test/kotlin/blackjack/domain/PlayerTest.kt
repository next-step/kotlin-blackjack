package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

class PlayerTest {

    @ParameterizedTest
    @ValueSource(strings = ["pobi", "jason"])
    fun `create player test`(name: String) {

        // WHEN
        val player = Player(name) { true }

        // THEN
        assertThat(player).isNotNull
        assertThat(player.name).isEqualTo(name)
        assertThat(player.cards).isEmpty()
        assertThat(player.score).isEqualTo(0)
        assertThat(player.isDrawable).isTrue()
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = [" ", "      "])
    fun `illegal player name test`(name: String) {
        assertThatThrownBy { Player(name) { true } }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageMatching("player name is not blank.")
    }

    @Test
    fun `receive card test`() {

        // GIVEN
        val playerName = "pobi"
        val receiveCard = Card(CardType.CLUB, CardNumber.QUEEN)

        // WHEN
        val player = Player(playerName) { true }
        player.receiveCard(receiveCard)

        // THEN
        assertThat(player).isNotNull
        assertThat(player.name).isEqualTo(playerName)
        assertThat(player.cards.size).isEqualTo(1)
        assertThat(player.cards).contains(receiveCard)
        assertThat(player.score).isEqualTo(CardNumber.QUEEN.score)
        assertThat(player.isDrawable).isTrue()
    }

    @Test
    fun `receive additional ace card test`() {

        // GIVEN
        val playerName = "pobi"
        val receiveCard = Card(CardType.CLUB, CardNumber.ACE)

        // WHEN
        val player = Player(playerName) { true }
        player.receiveCard(receiveCard)

        // THEN
        assertThat(player).isNotNull
        assertThat(player.name).isEqualTo(playerName)
        assertThat(player.cards.size).isEqualTo(1)
        assertThat(player.cards).contains(receiveCard)
        assertThat(player.score).isEqualTo(CardNumber.ACE.score + Game.ACE_ADDITIONAL_SCORE)
        assertThat(player.isDrawable).isTrue()
    }

    @Test
    fun `winner score card test`() {

        // GIVEN
        val playerName = "pobi"
        val receiveCards: Set<Card> = setOf(
            Card(CardType.CLUB, CardNumber.ACE),
            Card(CardType.DIAMOND, CardNumber.KING),
            Card(CardType.HEART, CardNumber.FOUR),
            Card(CardType.SPADE, CardNumber.SIX)
        )

        // WHEN
        val player = Player(playerName) { true }
        receiveCards.forEach {
            player.receiveCard(it)
        }

        // THEN
        assertThat(player).isNotNull
        assertThat(player.name).isEqualTo(playerName)
        assertThat(player.cards.size).isEqualTo(receiveCards.size)
        assertThat(player.cards).containsAll(receiveCards)
        assertThat(player.score).isEqualTo(Game.WINNER_SCORE)
        assertThat(player.isDrawable).isFalse()
    }

    @Test
    fun `isDrawable false test`() {

        // GIVEN
        val playerName = "pobi"
        val receiveCards: Set<Card> = setOf(
            Card(CardType.DIAMOND, CardNumber.KING),
            Card(CardType.HEART, CardNumber.SEVEN),
            Card(CardType.SPADE, CardNumber.SIX)
        )

        // WHEN
        val player = Player(playerName) { true }
        receiveCards.forEach {
            player.receiveCard(it)
        }

        // THEN
        assertThat(player).isNotNull
        assertThat(player.name).isEqualTo(playerName)
        assertThat(player.cards.size).isEqualTo(receiveCards.size)
        assertThat(player.cards).containsAll(receiveCards)
        assertThat(player.score).isEqualTo(receiveCards.map { it.number.score }.sum())
        assertThat(player.isDrawable).isFalse()
    }

    @Test
    fun `can not receive card test`() {

        // GIVEN
        val playerName = "pobi"
        val receiveCards: Set<Card> = setOf(
            Card(CardType.DIAMOND, CardNumber.KING),
            Card(CardType.HEART, CardNumber.SEVEN),
            Card(CardType.SPADE, CardNumber.SIX),
            Card(CardType.CLUB, CardNumber.ACE)
        )

        assertThatThrownBy {
            val player = Player(playerName) { true }
            receiveCards.forEach {
                player.receiveCard(it)
            }
        }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageMatching("player can not receive card.")
    }

    @Test
    fun `hit card test`() {

        // GIVEN
        val playerName = "pobi"
        val receiveCards: Set<Card> = setOf(
            Card(CardType.DIAMOND, CardNumber.KING),
            Card(CardType.HEART, CardNumber.SEVEN)
        )

        // WHEN
        val player = Player(playerName) { true }
        receiveCards.forEach {
            player.receiveCard(it)
        }
        player.hit(
            { Card(CardType.SPADE, CardNumber.SIX) },
            { println(player) }
        )

        // THEN
        assertThat(player).isNotNull
        assertThat(player.name).isEqualTo(playerName)
        assertThat(player.cards.size).isEqualTo(receiveCards.size + 1)
        assertThat(player.cards).containsAll(receiveCards)
        assertThat(player.score).isEqualTo(receiveCards.map { it.number.score }.sum() + CardNumber.SIX.score)
        assertThat(player.isDrawable).isFalse()
    }
}
