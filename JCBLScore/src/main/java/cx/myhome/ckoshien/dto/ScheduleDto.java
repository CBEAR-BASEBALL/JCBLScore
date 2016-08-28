package cx.myhome.ckoshien.dto;

import cx.myhome.ckoshien.action.api.v1.WeatherDto;
import cx.myhome.ckoshien.entity.MSchedule;

public class ScheduleDto extends MSchedule{
	/**
	 *
	 */
	private static final long serialVersionUID = -7582230131798763244L;

	private WeatherDto weatherDto;

	public WeatherDto getWeatherDto() {
		return weatherDto;
	}

	public void setWeatherDto(WeatherDto weatherDto) {
		this.weatherDto = weatherDto;
	}

}
