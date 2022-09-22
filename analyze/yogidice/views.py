# from .models import Test01
# from .serializers import Test01Serializer
from rest_framework.decorators import api_view
from rest_framework.response import Response 
from rest_framework import status

@api_view(['GET'])
# def test_list(request):
#     print('sp이거')
#     if request.method == 'GET':
#         name = Test01.objects.all()
#         serializer = Test01Serializer(name, many=True)
#         print(name)
#         return Response(serializer.data)