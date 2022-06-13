package blackjack.model.player

import blackjack.dummy.toCardSet
import blackjack.fixture.AlwaysHitDecisionMaker
import blackjack.model.CardDistributor
import blackjack.model.DefaultCardDistributor
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class PlayerRecordsTest {

    private lateinit var alwaysHitDecisionMaker: HitDecisionMaker
    private lateinit var cardDistributor: CardDistributor

    @BeforeEach
    fun setUp() {
        this.alwaysHitDecisionMaker = AlwaysHitDecisionMaker
        this.cardDistributor = DefaultCardDistributor()
    }

    @Test
    fun `기본 승패 계산 테스트`() {

        // given
        val defBetMoney = Player.MIN_BET_MONEY
        val dealer = Player.Dealer("딜러").apply {
            "JS,5S".toCardSet().forEach(this::addCard) // 15점 1승 2패
        }
        val playerA = Player.Guest("A", alwaysHitDecisionMaker, betMoney = defBetMoney).apply {
            "JS,6S".toCardSet().forEach(this::addCard) // 16점 승
        }
        val playerB = Player.Guest("B", alwaysHitDecisionMaker, betMoney = defBetMoney).apply {
            "JS,7S".toCardSet().forEach(this::addCard) // 17점 승
        }
        val playerC = Player.Guest("C", alwaysHitDecisionMaker, betMoney = defBetMoney).apply {
            "JS,4S".toCardSet().forEach(this::addCard) // 14점 패
        }

        val guests = Players(listOf(playerA, playerB, playerC))

        // when
        val actualRecords = PlayerRecords.of(dealer, guests)

        // then
        val expectedPlayerAEarn = playerA.betMoney
        val expectedPlayerBEarn = playerB.betMoney
        val expectedPlayerCEarn = -playerB.betMoney
        val expectedDealerEarn = -(expectedPlayerAEarn + expectedPlayerBEarn + expectedPlayerCEarn)

        val expectedDealerRecord =
            PlayerRecord.DealerRecord(dealer, win = 1, lose = 2, draw = 0, earnMoney = expectedDealerEarn)

        assertThat(actualRecords.find { it.player is Player.Dealer }).isEqualTo(expectedDealerRecord)
    }

    @Test
    fun `처음 2장으로 블랙잭 딜러는 블랙잭 아닌 경우 테스트 `() {

        // given
        val defBetMoney = Player.MIN_BET_MONEY

        val dealer = Player.Dealer("딜러").apply {
            "JS,5S".toCardSet().forEach(this::addCard) // 15점 블랙잭 아님
        }

        val playerA = Player.Guest("A", alwaysHitDecisionMaker, betMoney = defBetMoney).apply {
            "AS,QS".toCardSet().forEach(this::addCard) // 블랙잭 승
        }
        val playerB = Player.Guest("B", alwaysHitDecisionMaker, betMoney = defBetMoney).apply {
            "JS,7S".toCardSet().forEach(this::addCard) // 17점 승
        }
        val playerC = Player.Guest("C", alwaysHitDecisionMaker, betMoney = defBetMoney).apply {
            "JS,4S".toCardSet().forEach(this::addCard) // 14점 패
        }

        val guests = Players(listOf(playerA, playerB, playerC))

        // when
        val actualRecords = PlayerRecords.of(dealer, guests)

        // then
        val expectedWinCount = 1
        val expectedLoseCount = 2

        val expectedPlayerAEarn = (playerA.betMoney * PlayerRecord.REWARD_RATIO_OF_BLACK_JACK).toInt()
        val expectedPlayerBEarn = playerB.betMoney
        val expectedPlayerCEarn = -playerB.betMoney
        val expectedDealerEarn = -(expectedPlayerAEarn + expectedPlayerBEarn + expectedPlayerCEarn)

        val expectedDealerRecord =
            PlayerRecord.DealerRecord(
                dealer,
                win = expectedWinCount,
                lose = expectedLoseCount,
                earnMoney = expectedDealerEarn
            )

        assertAll(
            { assertThat(actualRecords.find { it.player is Player.Dealer }).isEqualTo(expectedDealerRecord) },
            {
                assertThat(actualRecords.find { it.player == playerA }?.earnMoney).isEqualTo(expectedPlayerAEarn)
            },
            { assertThat(actualRecords.find { it.player == playerB }?.earnMoney).isEqualTo(playerB.betMoney) },
            { assertThat(actualRecords.find { it.player == playerC }?.earnMoney).isEqualTo(-playerC.betMoney) }
        )
    }

    @Test
    fun `처음 2장으로 블랙잭 딜러도 블랙잭인 경우 테스트 `() {

        // given
        val defBetMoney = Player.MIN_BET_MONEY

        val dealer = Player.Dealer("딜러").apply {
            "AS,QS".toCardSet().forEach(this::addCard) // 블랙잭 1승 1패 1무
        }

        val playerA = Player.Guest("A", alwaysHitDecisionMaker, betMoney = defBetMoney).apply {
            "AS,QS".toCardSet().forEach(this::addCard) // 블랙잭 무승부 (돌려받음)
        }
        val playerB = Player.Guest("B", alwaysHitDecisionMaker, betMoney = defBetMoney).apply {
            "JS,7S".toCardSet().forEach(this::addCard) // 17점 패
        }
        val playerC = Player.Guest("C", alwaysHitDecisionMaker, betMoney = defBetMoney).apply {
            "JS,4S".toCardSet().forEach(this::addCard) // 14점 패
        }

        val guests = Players(listOf(playerA, playerB, playerC))

        // when
        val actualRecords = PlayerRecords.of(dealer, guests)

        // then
        val expectedWinCount = 2
        val expectedLoseCount = 0
        val expectedDrawCount = 1

        val expectedPlayerAEarn = 0
        val expectedPlayerBEarn = -playerB.betMoney
        val expectedPlayerCEarn = -playerB.betMoney
        val expectedDealerEarn = -(expectedPlayerAEarn + expectedPlayerBEarn + expectedPlayerCEarn)

        val expectedDealerRecord =
            PlayerRecord.DealerRecord(
                dealer,
                win = expectedWinCount,
                lose = expectedLoseCount,
                draw = expectedDrawCount,
                earnMoney = expectedDealerEarn
            )

        assertAll(
            { assertThat(actualRecords.find { it.player is Player.Dealer }).isEqualTo(expectedDealerRecord) },
            { assertThat(actualRecords.find { it.player == playerA }?.earnMoney).isEqualTo(expectedPlayerAEarn) },
            { assertThat(actualRecords.find { it.player == playerB }?.earnMoney).isEqualTo(expectedPlayerBEarn) },
            { assertThat(actualRecords.find { it.player == playerC }?.earnMoney).isEqualTo(expectedPlayerCEarn) }
        )
    }

    @Test
    fun `추가카드로 블랙잭, 딜러는 블랙잭 아닌 경우 테스트 `() {

        // given
        val defBetMoney = Player.MIN_BET_MONEY

        val dealer = Player.Dealer("딜러").apply {
            "JS,5S".toCardSet().forEach(this::addCard) // 15점 블랙잭 아님
        }

        val playerA = Player.Guest("A", alwaysHitDecisionMaker, betMoney = defBetMoney).apply {
            "AS,8S,2C".toCardSet().forEach(this::addCard) // 3장으로 블랙잭 승
        }
        val playerB = Player.Guest("B", alwaysHitDecisionMaker, betMoney = defBetMoney).apply {
            "JS,7S".toCardSet().forEach(this::addCard) // 17점 승
        }
        val playerC = Player.Guest("C", alwaysHitDecisionMaker, betMoney = defBetMoney).apply {
            "JS,4S".toCardSet().forEach(this::addCard) // 14점 패
        }

        val guests = Players(listOf(playerA, playerB, playerC))

        // when
        val actualRecords = PlayerRecords.of(dealer, guests)

        // then
        val expectedWinCount = 1
        val expectedLoseCount = 2

        val expectedPlayerAEarn = playerA.betMoney
        val expectedPlayerBEarn = playerB.betMoney
        val expectedPlayerCEarn = -playerB.betMoney
        val expectedDealerEarn = -(expectedPlayerAEarn + expectedPlayerBEarn + expectedPlayerCEarn)

        val expectedDealerRecord =
            PlayerRecord.DealerRecord(
                dealer,
                win = expectedWinCount,
                lose = expectedLoseCount,
                earnMoney = expectedDealerEarn
            )

        assertAll(
            { assertThat(actualRecords.find { it.player is Player.Dealer }).isEqualTo(expectedDealerRecord) },
            {
                assertThat(actualRecords.find { it.player == playerA }?.earnMoney).isEqualTo(expectedPlayerAEarn)
            },
            { assertThat(actualRecords.find { it.player == playerB }?.earnMoney).isEqualTo(playerB.betMoney) },
            { assertThat(actualRecords.find { it.player == playerC }?.earnMoney).isEqualTo(-playerC.betMoney) }
        )
    }

    @Test
    fun `처음 2장으로 블랙잭 딜러가 Bust 테스트 `() {

        // given
        val defBetMoney = Player.MIN_BET_MONEY

        val dealer = Player.Dealer("딜러").apply {
            "QS,5S,JS".toCardSet().forEach(this::addCard) // 버스트 3패
        }

        val playerA = Player.Guest("A", alwaysHitDecisionMaker, betMoney = defBetMoney).apply {
            "AS,QS".toCardSet().forEach(this::addCard) // 블랙잭 승 (1.5배 받음)
        }
        val playerB = Player.Guest("B", alwaysHitDecisionMaker, betMoney = defBetMoney).apply {
            "JS,7S".toCardSet().forEach(this::addCard) // 17점 승
        }
        val playerC = Player.Guest("C", alwaysHitDecisionMaker, betMoney = defBetMoney).apply {
            "JS,QS,KS".toCardSet().forEach(this::addCard) // 버스트 승
        }

        val guests = Players(listOf(playerA, playerB, playerC))

        // when
        val actualRecords = PlayerRecords.of(dealer, guests)

        // then
        val expectedPlayerAEarn = (defBetMoney * PlayerRecord.REWARD_RATIO_OF_BLACK_JACK).toInt()
        val expectedPlayerBEarn = defBetMoney
        val expectedPlayerCEarn = defBetMoney

        val expectedWinCount = 0
        val expectedLoseCount = 3
        val expectedDrawCount = 0
        val expectedDealerEarn = -(expectedPlayerAEarn + expectedPlayerBEarn + expectedPlayerCEarn)

        val expectedDealerRecord =
            PlayerRecord.DealerRecord(
                dealer,
                win = expectedWinCount,
                lose = expectedLoseCount,
                draw = expectedDrawCount,
                earnMoney = expectedDealerEarn
            )

        assertAll(
            { assertThat(actualRecords.find { it.player is Player.Dealer }).isEqualTo(expectedDealerRecord) },
            { assertThat(actualRecords.find { it.player == playerA }?.earnMoney).isEqualTo(expectedPlayerAEarn) },
            { assertThat(actualRecords.find { it.player == playerB }?.earnMoney).isEqualTo(expectedPlayerBEarn) },
            { assertThat(actualRecords.find { it.player == playerC }?.earnMoney).isEqualTo(expectedPlayerCEarn) }
        )
    }
}
