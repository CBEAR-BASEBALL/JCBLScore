package cx.myhome.ckoshien.util;

import java.util.Comparator;

import cx.myhome.ckoshien.dto.BattingResultDto;

public class HomerunComparator implements Comparator<BattingResultDto>{

	@Override
	public int compare(BattingResultDto o1, BattingResultDto o2) {
		if (o1.homerun > o2.homerun) {
            return 1;

        } else if (o1.homerun == o2.homerun) {
            return 0;

        } else {
            return -1;

        }

	}

}
