package blackjack.viewmodel

import blackjack.domain.BetAmount
import blackjack.domain.CardDeck
import blackjack.domain.PlayerInfo
import blackjack.domain.PlayerName
import blackjack.domain.PlayingCard
import blackjack.domain.START_CARD_COUNT
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BlackjackViewModelTest {
    private val dealerName = PlayerName("딜러")
    private val playerInfos = listOf(
        PlayerInfo(PlayerName("Spade"), BetAmount(10_000)),
        PlayerInfo(PlayerName("Diamond"), BetAmount(20_000))
    )
    private val viewModel: BlackjackViewModel
        get() = BlackjackViewModel.of(
            dealerName = dealerName,
            playerInfos = playerInfos,
            cardDeck = CardDeck.from(PlayingCard.all())
        )

    @Test
    fun `from에 PlayerName List를 넘겨 BlackjackViewModel을 생성할 수 있다`() {
        val viewModel = BlackjackViewModel.from(dealerName, playerInfos) { false }

        assertAll(
            { assertThat(viewModel.participants.dealer.name).isEqualTo(dealerName) },
            { assertThat(viewModel.participants.players).hasSize(playerInfos.size) }
        )
    }

    @Test
    fun `BlackJackViewModel을 생성할 때 각 플레이어들은 카드를 두 장씩 뽑는다`() {
        assertThat(viewModel.participants.all).allMatch { player ->
            player.hands.cards.size == START_CARD_COUNT
        }
    }

    @Test
    fun `uiEvent를 observe해서 출력해야 할 모델을 받을 수 있다`() {
        val viewModel = viewModel
        viewModel.hitEvent.observe { participant ->
            assertThat(participant).isEqualTo(viewModel.participants.all[2])
        }

        viewModel.hitEvent.emit(viewModel.participants.all[2])
    }
}
