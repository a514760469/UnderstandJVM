package com.ipint;

public class IpToInt {
	
	public static int ip2Int(String ipString) {
		// 取 ip 的各段
		String[] ipSlices = ipString.split("\\.");
		int rs = 0;
		for (int i = 0; i < ipSlices.length; i++) {
			// 将 ip 的每一段解析为 int，并根据位置左移 8 位
			int intSlice = Integer.parseInt(ipSlices[i]) << 8 * i;
			// 求与
			rs = rs | intSlice;
		}
		return rs;
	}
	
	public static void main(String[] args) {
		
		System.out.println(ip2Int("192.168.70.66"));
		
	}
}
