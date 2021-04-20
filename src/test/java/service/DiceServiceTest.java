//package service;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.runners.MockitoJUnitRunner;
//
////import static service.DiceService.roll;
////import static service.DiceService.rollEven;
//
//@RunWith(MockitoJUnitRunner.class)
//public class DiceServiceTest {
//
//    @Test
//    public void shouldRollFairWhenDiceTypeIsFair()
//    {
//       int diceValue =  roll();
//        Assert.assertTrue(diceValue>0);
//        Assert.assertTrue(diceValue<7);
//    }
//
//    @Test
//    public void shouldRollEvenWhenDiceTypeIsNotFair()
//    {
//        int diceValue =  rollEven();
//        Assert.assertTrue(diceValue>0);
//        Assert.assertTrue(diceValue<7);
//        Assert.assertEquals(0, diceValue % 2);
//    }
//}
