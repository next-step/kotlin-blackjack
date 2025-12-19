package service

import domain.CardDeck
import domain.Dealer
import domain.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CardDistributorServiceTest {
    private val cardDistributorService = CardDistributorService(CardDeck())

    @Test
    @DisplayName("플레이어와 딜러에게 각각 2장의 카드를 분배한다.")
    fun distributeTest() {
        // given
        val player = Player("A")
        val dealer = Dealer()

        // when
        cardDistributorService.distributeCards(listOf(player), dealer)

        // then
        assertThat(player.cardSize()).isEqualTo(CardDistributorService.PLAYER_CARD_COUNT)
        assertThat(dealer.cardSize()).isEqualTo(CardDistributorService.DEALER_CARD_COUNT)
    }

    @Test
    @DisplayName("플레이어와 딜러에게 각각 2장의 카드를 분배한다.")
    fun additionalDistributeTest() {
        // given
        val player = Player("A")
        val dealer = Dealer()
        cardDistributorService.distributeCards(listOf(player), dealer)
        val expectedDealerCardSize =
            if (dealer.score() > CardDistributorService.DEALER_ADDITIONAL_CARD_THRESHOLD) {
                CardDistributorService.DEALER_CARD_COUNT
            } else {
                CardDistributorService.DEALER_CARD_COUNT + 1
            }

        // when
        cardDistributorService.additionalDistributeForDealer(dealer)

        // then
        assertThat(dealer.cardSize()).isEqualTo(expectedDealerCardSize)
    }
}
