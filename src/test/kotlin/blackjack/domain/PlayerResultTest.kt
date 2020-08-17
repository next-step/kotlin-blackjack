// package blackjack.domain
//
// import blackjack.domain.card.Cards
// import blackjack.domain.player.Challenger
// import blackjack.domain.player.Dealer
// import blackjack.domain.result.GameResult
// import blackjack.domain.result.PlayerResult
// import org.assertj.core.api.Assertions.assertThat
// import org.junit.jupiter.params.ParameterizedTest
// import org.junit.jupiter.params.provider.Arguments
// import org.junit.jupiter.params.provider.MethodSource
// import java.util.stream.Stream
//
// class PlayerResultTest {
//
//     @MethodSource("dealerCardsAndChallengerCards")
//     @ParameterizedTest
//     fun `총점에 따른 게임 결과 확인`(dealerCards: Cards, challengerCards: Cards, challengerResult: PlayerResult) {
//         // given
//         val dealer = Dealer(dealerCards)
//         val challenger = Challenger("malibin", challengerCards)
//
//         // when
//         val playerResult = PlayerResult.ofChallengers(dealer, listOf(challenger))[0]
//
//         // then
//         assertThat(playerResult).isEqualTo(challengerResult)
//     }
//
//     companion object {
//         @JvmStatic
//         fun dealerCardsAndChallengerCards(): Stream<Arguments> {
//             return Stream.of(
//                 Arguments.of(
//                     Cards.denominationsOf("10", "10", "10"),
//                     Cards.denominationsOf("10", "10", "10"),
//                     PlayerResult("malibin", GameResult.WIN)
//                 ),
//                 Arguments.of(
//                     Cards.denominationsOf("10", "10", "10"),
//                     Cards.denominationsOf("10", "10"),
//                     PlayerResult("malibin", GameResult.WIN)
//                 ),
//                 Arguments.of(
//                     Cards.denominationsOf("10", "10"),
//                     Cards.denominationsOf("10", "10", "10"),
//                     PlayerResult("malibin", GameResult.LOSE)
//                 ),
//                 Arguments.of(
//                     Cards.denominationsOf("10", "10"),
//                     Cards.denominationsOf("10", "9"),
//                     PlayerResult("malibin", GameResult.LOSE)
//                 ),
//                 Arguments.of(
//                     Cards.denominationsOf("10", "9"),
//                     Cards.denominationsOf("10", "10"),
//                     PlayerResult("malibin", GameResult.WIN)
//                 )
//             )
//         }
//     }
// }
