package expression;

import lombok.Getter;

@Getter
public class AdditionResult {

  private Double sum = 0.0;

  private String result = "";

  private boolean isTextResult = false;

  public Object getResult() {
    if (isTextResult) {
      return result;
    } else {
      if ((sum % 1) == 0) {
        return sum.intValue();
      } else {
        return sum;
      }
    }
  }

  public void subtraction(Object value) {
    if (value instanceof Number) {
      if (isTextResult) {
        result = result.replaceAll(String.valueOf(value), "");
      } else {
        sum -= ((Number) value).doubleValue();
      }
    } else {
      result = result.replaceAll((String) value, "");
      isTextResult = true;
    }
  }

  public void addition(Object value) {
    if (value instanceof Number) {
      if (isTextResult) {
        result += value;
      } else {
        sum += ((Number) value).doubleValue();
      }
    } else {
      if (isTextResult) {
        result += value;
      } else {
        if (sum == 0) {
          result = (String) value;
        } else {
          if ((sum % 1) == 0) {
            result = sum.intValue() + (String) value;
          } else {
            result = sum + (String) value;
          }
        }
        isTextResult = true;
      }
    }
  }

}
