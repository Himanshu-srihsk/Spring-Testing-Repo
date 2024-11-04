package com.springtest.demo.service;

import com.springtest.demo.model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PlayerStatisticsTest {

    private static Player playerPatrickUnderThirty;
    private PlayerStatistics statisticsOfPatrickUnderThirty;

    @BeforeAll
    static void setup() {
        playerPatrickUnderThirty = new Player("Patrick", 27);
        System.out.println("Invoking setup method");
    }

    @BeforeEach
    void init() {
        statisticsOfPatrickUnderThirty = new PlayerStatistics(playerPatrickUnderThirty, 3, 3);
        System.out.println("Invoking init method");
    }

    @Test
    public void playerNameEqual() {
        System.out.println("test 1");
        Player player2 = new Player("Patrick", 25);

        // Assert that player names are equal, not the entire object
        // Assertions.assertEquals(player2.getName(), playerPatrickUnderThirty.getName());;
        assertThat(player2.getName()).isEqualTo(playerPatrickUnderThirty.getName());

    }

    @Test
    public void playerNamesNotEqual() {
        System.out.println("test 2");
        Player player2 = new Player("Kalvin", 25);
        assertThat(player2).isNotEqualTo(playerPatrickUnderThirty);
    }

    @Test
    public void youngerPlayerSame() {
        System.out.println("test 3");
        Player player2 = new Player("Patrick", 25);
        assertThat(PlayerStatistics.getYoungerPlayer(playerPatrickUnderThirty, player2)).isSameAs(player2);
    }

    @Test
    public void underThirtyTrue() {
        System.out.println("test 4");
        assertThat(statisticsOfPatrickUnderThirty.underThirty()).isTrue();
    }

    @Test
    public void underThirtyFalse() {
        System.out.println("test 5");
        Player player1 = new Player("Patrick", 37);
        PlayerStatistics statistics = new PlayerStatistics(player1, 3, 3);
        assertThat(statistics.underThirty()).isFalse();
    }

    @Test
    public void csvReportNull() {
        System.out.println("test 6");
        PlayerStatistics statistics = new PlayerStatistics(playerPatrickUnderThirty, 0, 0);
        assertThat(statistics.createCsvRecord()).isNull();
    }

    @Test
    public void csvReportNotNull() {
        System.out.println("test 7");
        PlayerStatistics statistics = new PlayerStatistics(playerPatrickUnderThirty, 3, 3);
        assertThat(statistics.createCsvRecord()).isNotNull();
    }

    @Test
    public void getCsvStatsRecord() {
        System.out.println("test 8");
        PlayerStatistics statistics = new PlayerStatistics(playerPatrickUnderThirty, 4, 8);
        Double[] expectedArray = {2d, 0.5};
        assertThat(statistics.createCsvRecord()).isEqualTo(expectedArray);
    }

    @Test
    public void playerConstructorNameAssigned() {
        Player player1 = new Player("Stuart", 30);
        assertThat(player1.getAge()).isEqualTo(30);
    }
}
