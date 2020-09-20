package com.walmart.walmarttestback.services.factories;

import com.walmart.walmarttestback.services.strategies.ISearchStrategy;
import com.walmart.walmarttestback.services.strategies.SearchByAllFieldsStrategy;
import com.walmart.walmarttestback.services.strategies.SearchByIdStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class SearchFactoryTest {

    @InjectMocks
    SearchFactory searchFactory;

    @Mock
    SearchByAllFieldsStrategy searchByAllFieldsStrategy;

    @Mock
    SearchByIdStrategy searchByIdStrategy;

    @Test
    public void ShouldReturnStrategyOperationByIdClassWhenSearchIsNumeric() {
        String searchQuery = "18";

        ISearchStrategy searchStrategy = searchFactory.getSearchStrategy(searchQuery);

        assertThat(searchStrategy).isInstanceOf(SearchByIdStrategy.class);
    }

    @Test
    public void ShouldReturnSearchByAllFieldsStrategyClassWhenSearchIsAlphanumericLengthGreaterThanThree() {
        String searchQuery = randomAlphanumeric(4);

        ISearchStrategy searchStrategy = searchFactory.getSearchStrategy(searchQuery);

        assertThat(searchStrategy).isInstanceOf(SearchByAllFieldsStrategy.class);
    }

    @Test
    public void ShouldReturnIllegalExceptionWhenSearchIsAlphanumericLessThanFour() {
        String searchQuery = randomAlphanumeric(2);

        assertThrows(IllegalArgumentException.class, () -> searchFactory.getSearchStrategy(searchQuery));
    }

}