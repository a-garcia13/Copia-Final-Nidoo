# wifi -0.110  -0.113
# lte -0.113  -0.099
# wcdma -0.101  -0.121
# gsm -0.121  -0.180

def send_pushy
  require 'httparty'

  url="https://api.pushy.me/push?api_key="+ENV['PUSHY_KEY']
    
  time=Time.now.localtime("-05:00").strftime('%Y-%m-%dT%H:%M:%S.%L')
  msg = {
    "to": ENV['DEVICE_ID'],
    "data": {
      "message": time
    }
  }
  result=HTTParty.post(url,body:msg)
end

def process_log(file='gsm')
  require 'json'
  require 'bigdecimal'
  require 'time'

  time_correction="-0.121  -0.180"
  File.open('gsm.csv','w') do |f|
    File.readlines(file).each do |line|
      if line.size > 2
        hash=JSON.parse(line[4..-1])
        send_time=Time.parse(hash['message'])
        receive_time=Time.parse(hash['time'])

        f_delta=receive_time-send_time

        i_delta=receive_time.to_i-send_time.to_i
        msi_delta=hash['time'].split('.').last.to_i - hash['message'].split('.').last.to_i
        i_delta+=(msi_delta/BigDecimal.new(1000))
        f.puts([hash['message'],hash['time'],f_delta,i_delta.to_s('F'),time_correction].join(','))
      end
    end
  end
end