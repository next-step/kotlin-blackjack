package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PlayerTest {
    private val YES = "y"
    private val heartCard = Card(Symbol.HEART, Numbers.FOUR)
    private val clubCard = Card(Symbol.CLUB, Numbers.JACK)
    private val cards = Cards(mutableListOf(heartCard, clubCard))
    private val player = Player("harry", cards)

    @DisplayName("플레이어가 생성되면 카드 두장을 가진다.")
    @Test
    fun `when created player than have two cards`() {
        assertThat(player.cards).isEqualTo(cards)
    }

    @DisplayName("y를 선택하고 카드 숫자의 합이 21보다 작으면 카드가 추가된다.")
    @Test
    fun `when select y than card is added`() {
        val addedCard = Card(Symbol.HEART, Numbers.FOUR)
        player.addMoreCards(YES, addedCard)
        assertThat(player.cards).isEqualTo(Cards(mutableListOf(heartCard, clubCard, addedCard)))
    }

    @DisplayName("y를 선택했지만 카드 숫자의 합이 21보다 크면 카드가 추가되지 않는다.")
    @Test
    fun `when selected y but the sum of the card numbers is greater than 21 than card will not be added`() {
        val addedCard = Card(Symbol.HEART, Numbers.JACK)
        assertThatThrownBy { player.addMoreCards(YES, addedCard) }
            .hasMessage("21을 초과하여 카드를 입력할 수 없습니다.")
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @DisplayName("n을 선택하면 카드가 추가되지 않는다.")
    @Test
    fun `when selected n than card will not be added`() {
        player.addMoreCards("n", heartCard)
        assertThat(player.cards).isEqualTo(Cards(mutableListOf(heartCard, clubCard)))
    }
}
