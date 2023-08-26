import com.example.case1.entity.CurrencyEntity;
import com.example.case1.repository.CurrencyRepository;
import com.example.case1.service.CurrencyService;
import com.example.case1.service.OpenExchangeRatesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTest {

    @InjectMocks
    private CurrencyService currencyService;

    @Mock
    private OpenExchangeRatesService openExchangeRatesService;

    @Mock
    private CurrencyRepository currencyRepository;

    @Test
    public void testGetCurrencyValues_CacheHit() {
        String date = "2023-08-21";
        CurrencyEntity cachedCurrency = new CurrencyEntity();
        cachedCurrency.setDate(date);
        when (openExchangeRatesService.getCurrencyData(anyString(),anyString())).thenReturn(cachedCurrency);

        currencyService.getCurrencyValues(date);
        CurrencyEntity result = currencyService.getCurrencyValues(date);


        assertNotNull(result);
        assertEquals(date, result.getDate());
    }


    @Test
    public void testGetCurrencyValues_CacheMiss() {
        String date = "2023-08-21";
        CurrencyEntity currencyFromClient = new CurrencyEntity();
        currencyFromClient.setDate(date);

        when(openExchangeRatesService.getCurrencyData(date, "USD")).thenReturn(currencyFromClient);

        CurrencyEntity result = currencyService.getCurrencyValues(date);

        verify(openExchangeRatesService).getCurrencyData(date, "USD");
        verify(currencyRepository).save(currencyFromClient);

        assertNotNull(result);
        assertEquals(date, result.getDate());
    }
}
