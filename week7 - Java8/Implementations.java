package com.hackbulgaria.corejava2;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.hackbulgaria.corejava2.data.Gender;
import com.hackbulgaria.corejava2.data.Student;
import com.hackbulgaria.corejava2.data.StudentsDataFactory;

public class Implementations implements StudentOperations {
	List<Student> allStudents;

	Implementations(List<Student> students) {
		allStudents = students;
	}

	public static void main(String[] args) {
		List<Student> allStudents = StudentsDataFactory.getAllStudents();
		Implementations o = new Implementations(allStudents);
		// System.out.println(o.getAverageMark());
		// System.out.println(o.getMarksDistributionByAge());
		System.out.println(o.splitStudentMarksByGenderAndThenByAge());
	}

	@Override
	public double getAverageMark() {
		return allStudents.stream().mapToDouble(s -> s.getGrade()).average()
				.getAsDouble();
	}

	@Override
	public List<Student> getAllPassing() {
		List<Student> passingStudents = allStudents.stream()
				.filter(s -> s.getGrade() >= 3.0f).collect(Collectors.toList());
		return passingStudents;
	}

	@Override
	public List<Student> getAllFailing() {
		List<Student> passingStudents = allStudents.stream()
				.filter(s -> s.getGrade() < 3.0f).collect(Collectors.toList());
		return passingStudents;
	}

	@Override
	public Map<Boolean, List<Student>> splitStudentsByMarks(float splitMark) {
		Map<Boolean, List<Student>> hm = new HashMap<Boolean, List<Student>>();
		List<Student> aboveMark = allStudents.stream()
				.filter(s -> s.getGrade() >= splitMark)
				.collect(Collectors.toList());
		List<Student> belowMark = allStudents.stream()
				.filter(s -> s.getGrade() < splitMark)
				.collect(Collectors.toList());
		hm.put(true, aboveMark);
		hm.put(false, belowMark);
		return hm;
	}

	@Override
	public List<Student> orderByMarkDescending() {
		return allStudents.stream()
				.sorted(Comparator.comparing(Student::getGrade).reversed())
				.collect(Collectors.toList());
	}

	@Override
	public List<Student> orderByMarkAscending() {
		return allStudents.stream()
				.sorted(Comparator.comparing(Student::getGrade))
				.collect(Collectors.toList());
	}

	@Override
	public List<Student> getStudentsWithLowestMarks() {
		double lowestMark = allStudents.stream().mapToDouble(s -> s.getGrade())
				.min().getAsDouble();
		List<Student> result = allStudents.stream()
				.filter(s -> s.getGrade() == lowestMark)
				.collect(Collectors.toList());
		return result;
	}

	@Override
	public List<Student> getStudentsWithHighestMarks() {
		double highestMark = allStudents.stream()
				.mapToDouble(s -> s.getGrade()).max().getAsDouble();
		List<Student> result = allStudents.stream()
				.filter(s -> s.getGrade() == highestMark)
				.collect(Collectors.toList());
		return result;
	}

	/*
	 * public List<Double> marksByAge(Integer age){ List<Double> result = new
	 * ArrayList<Double>(); for (Student s:allStudents){ if (s.getAge() == age){
	 * result.add(s.getGrade()); } } return result; }
	 */

	@Override
	public Map<Integer, List<Double>> getMarksDistributionByAge() {
		Map<Integer, List<Double>> hm = new HashMap<Integer, List<Double>>();
		allStudents.stream().forEach(
				s -> hm.put(
						s.getAge(),
						allStudents.stream()
								.filter(st -> st.getAge() == s.getAge())
								.map(Student::getGrade)
								.collect(Collectors.toList())));
		return hm;
	}

	@Override
	public Map<Gender, Double> getAverageMarkByGender() {
		Map<Gender, Double> hm = new HashMap<Gender, Double>();
		double womenAverage = allStudents.stream()
				.filter(s -> s.getGender().equals(Gender.FEMALE))
				.mapToDouble(Student::getGrade).average().getAsDouble();
		double menAverage = allStudents.stream()
				.filter(s -> s.getGender().equals(Gender.MALE))
				.mapToDouble(Student::getGrade).average().getAsDouble();
		hm.put(Gender.MALE, menAverage);
		hm.put(Gender.FEMALE, womenAverage);
		return hm;
	}

	@Override
	public Map<Double, Integer> getMarksDistribution() {
		Map<Double, Integer> hm = new HashMap<Double, Integer>();
		allStudents.stream().forEach(
				s -> hm.put(
						s.getGrade(),
						(int) allStudents.stream()
								.filter(st -> st.getGrade() == s.getGrade())
								.count()));
		return hm;
	}

	@Override
	public String getEmailToHeader() {
		StringBuilder sb = new StringBuilder();
		allStudents.stream().forEach(s -> sb.append(s.getEmail()).append(","));
		String result = sb.toString();
		return result.substring(0, result.length() - 1);
	}

	@Override
	public Map<Gender, Map<Integer, List<Student>>> splitStudentMarksByGenderAndThenByAge() {
		Map<Gender, Map<Integer, List<Student>>> hm = new HashMap<Gender, Map<Integer, List<Student>>>();
		Map<Integer, List<Student>> maleMarksByAge = new HashMap<Integer, List<Student>>();
		Map<Integer, List<Student>> femaleMarksByAge = new HashMap<Integer, List<Student>>();
		allStudents
				.stream()
				.filter(s -> s.getGender().equals(Gender.MALE))
				.forEach(
						std -> maleMarksByAge.put(
								std.getAge(),
								allStudents
										.stream()
										.filter(s -> s.getGender().equals(
												Gender.MALE))
										.filter(st -> st.getAge() == std
												.getAge())
										.collect(Collectors.toList())));
		allStudents
				.stream()
				.filter(s -> s.getGender().equals(Gender.FEMALE))
				.forEach(
						std -> femaleMarksByAge.put(
								std.getAge(),
								allStudents
										.stream()
										.filter(s -> s.getGender().equals(
												Gender.FEMALE))
										.filter(st -> st.getAge() == std
												.getAge())
										.collect(Collectors.toList())));

		hm.put(Gender.MALE, maleMarksByAge);
		hm.put(Gender.FEMALE, femaleMarksByAge);
		return hm;
	}

}
