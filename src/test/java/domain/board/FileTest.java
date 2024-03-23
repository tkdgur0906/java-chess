package domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FileTest {

    @Test
    @DisplayName("File 간의 차이 계산")
    void subtract() {
        File file1 = File.valueOf(1);
        File file2 = File.valueOf(2);

        assertThat(file1.subtract(file2)).isEqualTo(-1);
    }
}
