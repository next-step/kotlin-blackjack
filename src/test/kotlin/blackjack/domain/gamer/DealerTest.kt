package blackjack.domain.gamer

import blackjack.dto.GeneratePlayerRequest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `딜러가 게임에서 패배하면, 플레이어의 베팅금만큼 돈을 잃는다`() {
        // given
        val bettingMoney = 10000
        val playerName = "name"
        val player = GeneratePlayerRequest(playerName, bettingMoney)
            .generatePlayer()

        val dealer = Dealer()

        // when
        player.proceedGameRecord(GameRecordType.WIN)
        val playerWinMoney = player.winMoney()
        dealer.loseMoney(playerWinMoney)

        // then
        Assertions.assertThat(dealer.money).isEqualTo(-bettingMoney)
    }

    @Test
    fun `딜러가 게임에서 승리하면, 플레이어의 베팅금만큼 돈을 얻는다`() {
        // given
        val bettingMoney = 10000
        val playerName = "name"
        val player = GeneratePlayerRequest(playerName, bettingMoney)
            .generatePlayer()

        val dealer = Dealer()

        // when
        player.proceedGameRecord(GameRecordType.LOSE)
        val playerLoseMoney = player.loseMoney()
        dealer.winMoney(playerLoseMoney)

        // then
        Assertions.assertThat(dealer.money).isEqualTo(bettingMoney)
    }

    @Test
    fun `플레이어가 블랙잭으로 승리하면 딜러는 베팅한 금액의 일점오배를 잃는다`() {
        // given
        val bettingMoney = 10000
        val playerName = "name"
        val player = GeneratePlayerRequest(playerName, bettingMoney)
            .generatePlayer()

        val dealer = Dealer()

        // when
        player.proceedGameRecord(GameRecordType.WIN)
        val blackJackPlayerWinMoney = player.blackJackMoney()
        dealer.loseMoney(blackJackPlayerWinMoney)

        // then
        Assertions.assertThat(dealer.money).isEqualTo(-15000)
    }
}
