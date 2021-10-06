import http.client
conn = http.client.HTTPSConnection('www.naver.com')
conn.request('GET','/')
response = conn.getresponse()
data = response.read()
conn.close()
print (data)