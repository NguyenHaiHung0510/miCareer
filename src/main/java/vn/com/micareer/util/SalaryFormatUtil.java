package vn.com.micareer.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public final class SalaryFormatUtil {

    private static final Locale VIETNAMESE_LOCALE = new Locale("vi", "VN");

    private SalaryFormatUtil() {
    }

    public static String formatVnd(BigDecimal amount) {
        if (amount == null) {
            return "Thỏa thuận";
        }

        NumberFormat formatter = NumberFormat.getNumberInstance(VIETNAMESE_LOCALE);
        formatter.setMinimumFractionDigits(0);
        formatter.setMaximumFractionDigits(0);
        return formatter.format(amount) + " đ";
    }

    public static String formatRange(BigDecimal minSalary, BigDecimal maxSalary) {
        if (minSalary == null && maxSalary == null) {
            return "Thỏa thuận";
        }
        if (minSalary == null) {
            return "Đến " + formatVnd(maxSalary);
        }
        if (maxSalary == null) {
            return "Từ " + formatVnd(minSalary);
        }
        return formatVnd(minSalary) + " - " + formatVnd(maxSalary);
    }
}