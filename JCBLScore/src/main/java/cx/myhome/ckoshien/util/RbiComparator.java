package cx.myhome.ckoshien.util;

import java.util.Comparator;

import cx.myhome.ckoshien.dto.BattingResultDto;

public class RbiComparator implements Comparator<BattingResultDto>{

	@Override
	public int compare(BattingResultDto o1, BattingResultDto o2) {
		if (o1.rbi > o2.rbi) {
            return 1;

        } else if (o1.rbi == o2.rbi) {
            return 0;

        } else {
            return -1;

        }

	}

}
