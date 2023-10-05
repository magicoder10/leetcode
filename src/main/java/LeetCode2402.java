import java.util.Arrays;
import java.util.Comparator;

public class LeetCode2402 {
    public int mostBooked(int n, int[][] meetings) {
        if (n < 1) {
            return 0;
        }

        if (meetings == null || meetings.length < 1) {
            return 0;
        }

        // Sort meetings by start time
        Arrays.sort(meetings, Comparator.comparingInt((int[] meeting) -> meeting[0]));

        int[] meetingCounts = new int[n];
        long[] meetingEndBefore = new long[n];

        int maxMeetingCount = 0;
        int lowestRoomNumber = 0;
        for (int[] meeting : meetings) {
            int roomNumber = this.findMeetingRoom(meetingEndBefore, meeting);

            // meeting = [4, 7]
            // meetingEndBefore[0] = 6
            // delay = 2
            // meetingEndBefore[0] = 7 + 2 = 9
            long delay = Math.max(0, meetingEndBefore[roomNumber] - meeting[0]);
            meetingEndBefore[roomNumber] = delay + meeting[1];
            meetingCounts[roomNumber]++;

            if (meetingCounts[roomNumber] > maxMeetingCount) {
                maxMeetingCount = meetingCounts[roomNumber];
                lowestRoomNumber = roomNumber;
            } else if (meetingCounts[roomNumber] == maxMeetingCount &&
                    roomNumber < lowestRoomNumber) {
                lowestRoomNumber = roomNumber;
            }
        }

        return lowestRoomNumber;
    }

    private int findMeetingRoom(long[] meetingEndBefore, int[] meeting) {
        long minMeetingEndBefore = Long.MAX_VALUE;
        int minRoomNumber = Integer.MAX_VALUE;
        for (int roomNumber = 0; roomNumber < meetingEndBefore.length; roomNumber++) {
            if (meeting[0] >= meetingEndBefore[roomNumber]) {
                return roomNumber;
            }

            if (meetingEndBefore[roomNumber] < minMeetingEndBefore) {
                minMeetingEndBefore = meetingEndBefore[roomNumber];
                minRoomNumber = roomNumber;
            }
        }

        return minRoomNumber;
    }
}
