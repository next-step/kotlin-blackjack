package blackjack

import blackjack.model.Card
import blackjack.model.Rank
import blackjack.model.Suit
import org.junit.jupiter.api.Test
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.DisplayName

class CardTest {

    @DisplayName("Rank가 ACE인 카드는 isAce가 true이어야 한다.")
    @Test
    fun `Rank가 ACE인 카드는 isAce가 true이어야 한다`() {
        val aceCard = Card.createCard(Suit.HEARTS, Rank.ACE)
        aceCard.isAce shouldBe true
    }

    @DisplayName("Rank가 ACE가 아닌 카드는 isAce가 false이어야 한다.")
    @Test
    fun `Rank가 ACE가 아닌 카드는 isAce가 false이어야 한다`() {
        val fiveCard = Card.createCard(Suit.SPADES, Rank.FIVE)
        fiveCard.isAce shouldBe false
    }

    @DisplayName("같은 Suit와 Rank를 가진 두 카드는 같아야 한다.")
    @Test
    fun `같은 Suit와 Rank를 가진 두 카드는 같아야 한다`() {
        val card1 = Card.createCard(Suit.HEARTS, Rank.QUEEN)
        val card2 = Card.createCard(Suit.HEARTS, Rank.QUEEN)
        card1 shouldBe card2
    }

    @DisplayName("다른 Suit를 가진 카드는 같지 않아야 한다.")
    @Test
    fun `다른 Suit를 가진 카드는 같지 않아야 한다`() {
        val card1 = Card.createCard(Suit.HEARTS, Rank.QUEEN)
        val card2 = Card.createCard(Suit.DIAMONDS, Rank.QUEEN)
        card1 shouldNotBe card2
    }

    @DisplayName("다른 Rank를 가진 카드는 같지 않아야 한다.")
    @Test
    fun `다른 Rank를 가진 카드는 같지 않아야 한다`() {
        val card1 = Card.createCard(Suit.HEARTS, Rank.QUEEN)
        val card2 = Card.createCard(Suit.HEARTS, Rank.KING)
        card1 shouldNotBe card2
    }

    @DisplayName("Card 생성 시 동일한 Suit와 Rank를 가진 카드만 생성할 수 있다.")
    @Test
    fun `Card 생성 시 동일한 Suit와 Rank를 가진 카드만 생성할 수 있다`() {
        val card1 = Card.createCard(Suit.SPADES, Rank.THREE)
        val card2 = Card.createCard(Suit.SPADES, Rank.THREE)
        card1 shouldBe card2
    }

    @DisplayName("Card의 hashCode는 동일한 Suit와 Rank를 가진 카드에서 동일해야 한다.")
    @Test
    fun `Card의 hashCode는 동일한 Suit와 Rank를 가진 카드에서 동일해야 한다`() {
        val card1 = Card.createCard(Suit.CLUBS, Rank.FOUR)
        val card2 = Card.createCard(Suit.CLUBS, Rank.FOUR)
        card1.hashCode() shouldBe card2.hashCode()
    }
}