# -0.094 -0.081

# -0.055  -0.079
def send_sns
  require 'aws-sdk-sns'
  topic_arn='arn:aws:sns:us-east-1:873727435923:uniandes-gcm-push-test'


  time=Time.now.localtime("-05:00").strftime('%Y-%m-%dT%H:%M:%S.%L')
  gcm={data:{message:time}}.to_json
  msg={default:time,GCM:gcm}.to_json
  client=Aws::SNS::Client.new(region:'us-east-1')
  client.publish(topic_arn:topic_arn, message:msg,message_structure:'json', subject:'subject')
end


def process_log(file='Lte.log')
  require 'json'
  require 'bigdecimal'
  require 'time'

  time_correction="-0.118  -0.121"
  File.open('lte2.csv','w') do |f|
    File.readlines(file).each do |line|
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