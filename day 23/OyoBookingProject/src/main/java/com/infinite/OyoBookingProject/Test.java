package com.infinite.OyoBookingProject;

import java.util.List;

public class Test {
	public static void main(String[] args) {
		RoomDAO obj = new RoomDAO();
		List<String> list =  obj.showAvailableRooms();
		System.out.println(list.size());
	}
}