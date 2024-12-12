package blackjack

import blackjack.InitialCardsTestFixtures.blackjackCards
import blackjack.InitialCardsTestFixtures.initial16Cards
import blackjack.InitialCardsTestFixtures.initial18Cards
import blackjack.InitialCardsTestFixtures.initial20Cards
import blackjack.ui.DrawAnswer
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.ints.shouldBeZero
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

class BlackjackServiceTest : BehaviorSpec({
    given("BlackjackService 는") {
        val deck = Deck()
        val sut = BlackjackService(deck)

        `when`("playerNames 를 받아") {
            val playerNames: List<String> = listOf("y2gcoder", "hong")
            val (dealer, players) = sut.initializeParticipants(playerNames)

            then("참가자들을 초기화할 수 있다") {
                dealer.shouldBeTypeOf<Dealer>()
                players.size shouldBe playerNames.size
                deck.size() shouldBe (52 - 6)
            }
        }

        `when`("players 와 베팅 금액 입력 받는 람다를 받아") {
            val players =
                listOf(
                    Player("y2gcoder", initial18Cards),
                    Player("hong", initial20Cards),
                )

            sut.betAllPlayers(players) { 10000L }

            then("참가자들이 베팅할 수 있게 한다") {
                players.forEach { player ->
                    player.betAmount shouldBe Money(10000)
                }
            }
        }

        `when`("players 와 drawAnswer, player의 카드 표시 람다, player의 onBust(람다), onBlackjack(람다)를 받아") {
            val players =
                listOf(
                    Player("y2gcoder", blackjackCards),
                    Player("hong", initial20Cards),
                )

            val revealedPlayers: MutableList<Participant> = mutableListOf()
            val bustPlayers: MutableList<Participant> = mutableListOf()
            val blackjackPlayers: MutableList<Participant> = mutableListOf()

            sut.handleAllPlayersTurn(
                players = players,
                drawAnswer = { DrawAnswer.N },
                revealParticipantHeldCards = { participant: Participant -> revealedPlayers.add(participant) },
                onBust = { participant: Participant -> bustPlayers.add(participant) },
                onBlackjack = { participant: Participant -> blackjackPlayers.add(participant) },
            )

            then("모든 player 들의 턴을 진행할 수 있다") {
                players[0].state.shouldBeTypeOf<Blackjack>()
                players[1].state.shouldBeTypeOf<Stay>()

                revealedPlayers.size.shouldBeZero()

                bustPlayers.size.shouldBeZero()

                blackjackPlayers.size shouldBe 1
                blackjackPlayers[0] shouldBe players[0]
            }
        }

        `when`("dealer 와 dealer 의 onUnderOverOutput 람다를 받아") {
            val dealer = Dealer(initialCards = initial16Cards)

            val bustPlayers: MutableList<Participant> = mutableListOf()
            val blackjackPlayers: MutableList<Participant> = mutableListOf()

            sut.handleDealerTurn(
                dealer = dealer,
                onUnderOverOutput = {},
                onBust = { participant: Participant -> bustPlayers.add(participant) },
                onBlackjack = { participant: Participant -> blackjackPlayers.add(participant) },
            )

            then("dealer 의 턴을 진행할 수 있다") {
                dealer.state.isFinished() shouldBe true
            }
        }

        `when`("Dealer 와 Player 목록을 받아") {
            val dealer = Dealer(initialCards = initial18Cards)
            dealer.stay()

            val player1 = Player("y2gcoder", blackjackCards)
            player1.bet(10000)
            val player2 = Player("hong", initial20Cards)
            player2.bet(20000)
            player2.stay()

            val players = listOf(player1, player2)

            val profits: List<Pair<Participant, Money>> = sut.calculateAllPlayerProfits(dealer, players)

            then("각 참가자별 수익 목록을 구할 수 있다") {
                profits.size shouldBe 3

                profits[0].first shouldBe dealer
                profits[0].second shouldBe Money(-35000)

                profits[1].first shouldBe player1
                profits[1].second shouldBe Money(15000)

                profits[2].first shouldBe player2
                profits[2].second shouldBe Money(20000)
            }
        }
    }
})
