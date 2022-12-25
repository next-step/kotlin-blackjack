package blackjack

import blackjack.domain.BlackJackMachine
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.card.Cards
import blackjack.domain.dto.BettingMoneyResult
import blackjack.domain.dto.ParticipantMoneyResult
import blackjack.domain.enums.WinOrLose
import blackjack.domain.person.Dealer
import blackjack.domain.person.Participant
import blackjack.domain.person.Player
import blackjack.domain.strategy.SequentialCardPickStrategy
import blackjack.view.OutputView
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class BlackJackMachineTest : BehaviorSpec({
    Given("초기 세팅에서 ") {
        val dealer = Dealer(name = "딜러", cardPickStrategy = SequentialCardPickStrategy())
        val participant = Participant("길상현", 10000L)
        val blackJackMachine = BlackJackMachine(dealer = dealer, players = listOf(participant, dealer))
        When("진행하면 ") {
            blackJackMachine.initialize()
            Then("카드가 2장이다.") {
                participant.cards.cards.size shouldBe 2
            }
        }

        When("카드를 추가하면 조건만큼 ") {
            blackJackMachine.addCard({ player: Player -> player.cards.cards.size <= 2 }, OutputView::printCardState)
            Then("카드를 추가한다.") {
                participant.cards.cards.size shouldBe 3
            }
        }

        val hasCardDealer = Dealer("딜러", 0L, Cards(mutableListOf(Card(CardShape.CLOVER, CardNumber.QUEEN), Card(CardShape.CLOVER, CardNumber.NUM_8))), SequentialCardPickStrategy())
        val hasCardParticipant = Participant("길상현", 10000L, Cards(mutableListOf(Card(CardShape.CLOVER, CardNumber.QUEEN), Card(CardShape.CLOVER, CardNumber.NUM_9))))
        val secondBlackJackMachine = BlackJackMachine(
            dealer = hasCardDealer,
            players = listOf(hasCardParticipant, hasCardDealer)
        )
        When("결과를 조회하면 ") {
            val gameResult = secondBlackJackMachine.getGameResult()
            Then("결과를 가져온다.") {
                gameResult.dealerName shouldBe "딜러"
                gameResult.participantResult.first().name shouldBe "길상현"
                gameResult.participantResult.first().winOrLose shouldBe WinOrLose.WIN
            }
        }
    }

    Given("블랙잭 게임에서 ") {
        // 처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 딜러에게 받는다.
        val dealer1 = Dealer(name = "딜러", cardPickStrategy = SequentialCardPickStrategy(), cards = Cards(mutableListOf(Card(CardShape.CLOVER, CardNumber.QUEEN))))
        val participant1 = Participant("길상현", 10000L, cards = Cards(mutableListOf(Card(CardShape.CLOVER, CardNumber.QUEEN), Card(CardShape.CLOVER, CardNumber.ACE))))
        When("참가자가 처음 두 장의 카드 합이 21일 경우 ") {
            val machine = BlackJackMachine(dealer = dealer1, players = listOf(dealer1, participant1))
            Then("베팅 금액의 1.5배를 딜러에게 받는다.") {
                machine.getBettingResult() shouldBe BettingMoneyResult("딜러", listOf(ParticipantMoneyResult("길상현", 15000L)))
            }
        }

        // 딜러와 참가자가 모두 21일 경우 배팅 금액을 딜러에게 받는다.
        val dealer2 = Dealer(name = "딜러", cardPickStrategy = SequentialCardPickStrategy(), cards = Cards(mutableListOf(Card(CardShape.CLOVER, CardNumber.QUEEN), Card(CardShape.CLOVER, CardNumber.ACE))))
        val participant2 = Participant("길상현", 10000L, cards = Cards(mutableListOf(Card(CardShape.CLOVER, CardNumber.QUEEN), Card(CardShape.CLOVER, CardNumber.ACE))))
        When("참가자가 처음 두 장의 카드 합이 21일 경우 ") {
            val machine = BlackJackMachine(dealer = dealer2, players = listOf(dealer2, participant2))
            Then("베팅 금액의 1.5배를 딜러에게 받는다.") {
                machine.getBettingResult() shouldBe BettingMoneyResult("딜러", listOf(ParticipantMoneyResult("길상현", 10000L)))
            }
        }

        // 딜러가 21을 초과하면 배팅 금액을 딜러에게 받는다.
        val dealer3 = Dealer(name = "딜러", cardPickStrategy = SequentialCardPickStrategy(), cards = Cards(mutableListOf(Card(CardShape.CLOVER, CardNumber.QUEEN), Card(CardShape.CLOVER, CardNumber.QUEEN), Card(CardShape.CLOVER, CardNumber.QUEEN))))
        val participant3 = Participant("길상현", 10000L, cards = Cards(mutableListOf(Card(CardShape.CLOVER, CardNumber.QUEEN))))
        When("참가자가 처음 두 장의 카드 합이 21일 경우 ") {
            val machine = BlackJackMachine(dealer = dealer3, players = listOf(dealer3, participant3))
            Then("베팅 금액의 1.5배를 딜러에게 받는다.") {
                machine.getBettingResult() shouldBe BettingMoneyResult("딜러", listOf(ParticipantMoneyResult("길상현", 10000L)))
            }
        }

        // 딜러에게서 승리한다면, 베팅 금액을 딜러에게 받는다.
        val dealer4 = Dealer(name = "딜러", cardPickStrategy = SequentialCardPickStrategy(), cards = Cards(mutableListOf(Card(CardShape.CLOVER, CardNumber.QUEEN), Card(CardShape.CLOVER, CardNumber.NUM_9))))
        val participant4 = Participant("길상현", 10000L, cards = Cards(mutableListOf(Card(CardShape.CLOVER, CardNumber.QUEEN), Card(CardShape.CLOVER, CardNumber.QUEEN))))
        When("참가자가 처음 두 장의 카드 합이 21일 경우 ") {
            val machine = BlackJackMachine(dealer = dealer4, players = listOf(dealer4, participant4))
            Then("베팅 금액의 1.5배를 딜러에게 받는다.") {
                machine.getBettingResult() shouldBe BettingMoneyResult("딜러", listOf(ParticipantMoneyResult("길상현", 10000L)))
            }
        }

        // 딜러에게 패배한다면, 딜러에게 배팅 금액을 지불한다.
        val dealer5 = Dealer(name = "딜러", cardPickStrategy = SequentialCardPickStrategy(), cards = Cards(mutableListOf(Card(CardShape.CLOVER, CardNumber.QUEEN), Card(CardShape.CLOVER, CardNumber.QUEEN))))
        val participant5 = Participant("길상현", 10000L, cards = Cards(mutableListOf(Card(CardShape.CLOVER, CardNumber.QUEEN), Card(CardShape.CLOVER, CardNumber.NUM_9))))
        When("참가자가 처음 두 장의 카드 합이 21일 경우 ") {
            val machine = BlackJackMachine(dealer = dealer5, players = listOf(dealer5, participant5))
            Then("베팅 금액의 1.5배를 딜러에게 받는다.") {
                machine.getBettingResult() shouldBe BettingMoneyResult("딜러", listOf(ParticipantMoneyResult("길상현", -10000L)))
            }
        }
    }
})
