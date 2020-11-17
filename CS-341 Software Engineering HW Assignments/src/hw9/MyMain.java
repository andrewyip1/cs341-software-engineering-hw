package hw9;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MyMain {
// The consecutive hours developer codes.
	private static final List<Integer> hourData = Arrays.asList(2, 3, 5, 7, 9, 11, 14); // The number of bugs produces.
	private static final List<Integer> bugData = Arrays.asList(4, 5, 7, 10, 15, 20, 40);

	public static void main(String[] args) {
		System.out.println("The #bugs predicted for 13 hours of code is " + predictForValue(13));
	}

	private static Double predictForValue(int predictForDependentVariable) { // CHECK IF DATA DOES NOT CORRESPOND
																				// CORRECTLY.
		if (hourData.size() != bugData.size())
			throw new IllegalStateException("Must have equal X and Y data points");
// TASK 1: HOW MANY DATA VALUES ARE THERE?
		Integer numberOfDataValues = hourData.size();
		System.out.println("Task 1: How much data? " + numberOfDataValues);
// TASK 2: CREATE A lINKED LIST STRUCTURE TO HOLD HOURS SQUARED.
		List<Double> hoursSquared = hourData.stream().map(position -> Math.pow(position, 2))
				.collect(Collectors.toList());
		System.out.println("Task 2: hoursSquared = " + hoursSquared);
// TASK 3: CREATE A LIST STRUCTURE TO HOLD THE HOURS MULTIPLIED BY THE BUGS
		List<Integer> hoursMultipliedBybugs = IntStream.range(0, numberOfDataValues)
				.map(i -> hourData.get(i) * bugData.get(i)).boxed().collect(Collectors.toList());
		System.out.println("Task 3: hoursMultipliedBybugs = " + hoursMultipliedBybugs);
// TASK 4: COMPUTE THE HOURS SUMMED AND THE BUGS SUMMED
		Integer hoursSummed = hourData.stream().reduce((prev, next) -> prev + next).get();
		System.out.println("Task 4a: hoursSummed = " + hoursSummed);
		Integer bugsSummed = bugData.stream().reduce((prev, next) -> prev + next).get();
		System.out.println("Task 4b: bugsSummed = " + bugsSummed);
// TASK 5: COMPUTE THE SUM OF HOURS SQUARED
		Double sumOfHourSquared = hoursSquared.stream().reduce((prev, next) -> prev + next).get();
		System.out.println("Task 5: sumOfHourSquared = " + sumOfHourSquared);
// TASK 6: COMPUTE THE SUM OF HOURS MULTIPLIED BY BUGS.
		Integer sumOfHoursMultipliedByBugs = hoursMultipliedBybugs.stream().reduce((prev, next) -> prev + next).get();
		System.out.println("Task 6: sumOfHoursMultipliedByBugs = " + sumOfHoursMultipliedByBugs);
// TASK 7: COMPUTE THE SLOP. BEGIN BY COMPUTING THE SLOPE NOMINATOR AND DENOMINATOR
		int slopeNominator = numberOfDataValues * sumOfHoursMultipliedByBugs - bugsSummed * hoursSummed;
		Double slopeDenominator = numberOfDataValues * sumOfHoursMultipliedByBugs - Math.pow(hoursSummed, 2);
		Double slope = slopeNominator / slopeDenominator;
// TASK 8: COMPUTE THE INTERCEPT. BEGIN BY COMPUTING THE INTERCEPT NOMINATOR AND // DENOMINATOR.
		double interceptNominator = bugsSummed - slope * hoursSummed;
		double interceptDenominator = numberOfDataValues;
		Double intercept = interceptNominator / interceptDenominator;
// TASK 9: COMPUTE AND RETURN THE PREDICTED NUMBER OF BUGS.
		double predictedBugs = (slope * predictForDependentVariable) + intercept;
		return predictedBugs;
	}
}