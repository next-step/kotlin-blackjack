package blackjack.domain

import org.junit.jupiter.api.Test

internal class GameResultCalculatorTest {
    @Test
    fun `딜러와 플레이어가 블랙잭을 완성하였을 시 무승부(push)`() {
    }

    @Test
    fun `딜러 블랙잭 완성, 플레이어가 블랙잭 미완성하였을 시 딜러 승`() {
    }

    @Test
    fun `딜러 블랙잭 미완성, 플레이어가 블랙잭 완성하였을 시 플레이어 승`() {
    }

    @Test
    fun `딜러 버스트, 플레이어 버스트 상태 아닌 경우 플레이어 승`() {
    }

    @Test
    fun `플레이어 버스트 상태이면 딜러 승`() {
    }

    @Test
    fun `딜러, 플레이어 각각 블랙잭, 버스트 상태 아니고 카드의 총합이 동일하면 무승부(push)`() {
    }

    @Test
    fun `딜러, 플레이어 각각 블랙잭, 버스트 상태 아니고 카드의 총합이 딜러가 크면 딜러 승`() {
    }

    @Test
    fun `딜러, 플레이어 각각 블랙잭, 버스트 상태 아니고 카드의 총합이 플레이어가 크면 플레이어 승`() {
    }
}
