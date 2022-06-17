// package blackjack.dealer
//
// import blackjack.card.Deck
// import blackjack.player.Player
// import org.assertj.core.api.Assertions.assertThat
// import org.junit.jupiter.api.Test
//
// class DealerTest {
//
//    @Test
//    fun `딜러는 덱에서 카드를 뽑아 플레이어에게 줄 수 있다`() {
//        val deck = Deck.init()
//        val dealer = Dealer(deck)
//        val player = Player("pang")
//
//        assertThat(player.show()).isEmpty()
//        dealer.giveCard(player)
//
//        assertThat(player.show().size).isEqualTo(1)
//    }
// }
