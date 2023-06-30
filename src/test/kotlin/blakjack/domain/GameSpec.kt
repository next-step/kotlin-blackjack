package blakjack.domain

import blakjack.domain.Participant.ParticipantAction
import blakjack.domain.extension.cards
import blakjack.domain.extension.heart10
import blakjack.domain.extension.heart2
import blakjack.domain.extension.heart3
import blakjack.domain.extension.heart9
import blakjack.domain.extension.heartKing
import blakjack.domain.extension.spade10
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class GameSpec : DescribeSpec({
    describe("초기 카드 뽑기 검증") {
        context("초기 카드 뽑기가 실행되면") {
            val dealer = Dealer()
            val player = Player(name = "플레이어")
            val game = Game(dealer = dealer, players = listOf(player))

            game.initialDraw()

            it("플레이어는 카드 2장을 갖는다.") {
                player.cards.size shouldBe 2
            }
            it("딜러는 카드 2장을 갖는다.") {
                dealer.cards.size shouldBe 2
            }
        }
    }

    describe("플레이어 HIT(카드 추가) 검증") {
        context("플레이어에 카드 1장을 추가하면") {
            val player = Player("홍길동")
            val game = Game(players = listOf(player))

            it("카드 목록에 카드 1장이 추가된다.") {
                game.hit(player)

                player.cards.size shouldBe 1
            }
        }

        context("카드 추가 후 플레이어의 점수가 21점을 초과하면") {
            val player = Player("홍길동").also { it.add(cards(heart10, heart9, heart2)) }
            val game = Game(players = listOf(player))

            it("플레이어는 BUST 상태가 된다.") {
                game.hit(player)

                player.isBust() shouldBe true
            }
        }
    }

    describe("딜러 Hit or Stand 검증") {
        context("딜러의 점수가 17점 이상이면") {
            val dealer = Dealer().also { it.add(cards(heart9, heart10)) }
            val game = Game(dealer = dealer, players = listOf(Player("홍길동")))

            it("딜러는 STAND 한다.") {
                game.hitOrStandDealer() shouldBe ParticipantAction.STAND
                dealer.cards.size shouldBe 2
            }
        }

        context("딜러의 점수가 17점 미만이면") {
            val dealer = Dealer().also { it.add(cards(heart2, heart3)) }
            val game = Game(dealer = dealer, players = listOf(Player("홍길동")))

            it("딜러는 HIT 한다.") {
                game.hitOrStandDealer() shouldBe ParticipantAction.HIT
                dealer.cards.size shouldBe 3
            }
        }

        context("카드 추가 후 딜러의 점수가 21점을 초과하면") {
            val cardDeck = CardDeck(cards = cards(spade10))
            val dealer = Dealer(cardDeck = cardDeck).also { it.add(cards(heart2, heart10)) }
            val game = Game(dealer = dealer, players = listOf(Player("홍길동")))

            it("딜러는 BUST 상태가 된다.") {
                game.hitOrStandDealer()
                dealer.isBust() shouldBe true
            }
        }
    }

    describe("게임 결과 검증") {
        context("딜러가 BUST 상태인 경우") {
            val dealer = Dealer().also { it.add(cards(heart10, heart9, spade10)) }

            it("BUST 상태가 아닌 플레이어는 승리한다.") {
                val playerA = Player("A").also { it.add(cards(heart10, heart9)) }
                val playerB = Player("B").also { it.add(cards(heart10, heart9, spade10)) }
                val game = Game(dealer = dealer, players = listOf(playerA, playerB))

                game.result()

                playerA.result shouldBe Player.Result.WIN
                playerB.result shouldBe Player.Result.LOSE
                dealer.winCount shouldBe 1
                dealer.loseCount shouldBe 1
            }
        }

        context("딜러가 BUST 상태가 아닌 경우") {
            val dealer = Dealer().also { it.add(cards(heart10, heart9)) }

            it("딜러보다 점수가 높은 플레이어는 승리한다.") {
                val winPlayer = Player("A").also { it.add(cards(heart10, heartKing)) }
                val losePlayer = Player("B").also { it.add(cards(heart10, heart2)) }
                val game = Game(dealer = dealer, players = listOf(winPlayer, losePlayer))

                game.result()

                winPlayer.result shouldBe Player.Result.WIN
                losePlayer.result shouldBe Player.Result.LOSE
                dealer.winCount shouldBe 1
                dealer.loseCount shouldBe 1
            }
        }
    }
})
