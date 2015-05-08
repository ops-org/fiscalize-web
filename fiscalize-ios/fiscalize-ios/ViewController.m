//
//  ViewController.m
//  fiscalize-ios
//
//  Created by Jhonatan Richard Raphael on 5/8/15.
//  Copyright (c) 2015 OPS. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    
    NSURLRequest *request = [[NSURLRequest alloc] initWithURL:[NSURL URLWithString:@"http://104.131.229.175/ops"]];
    
    __block NSDictionary *json;
    [NSURLConnection sendAsynchronousRequest:request
                                       queue:[NSOperationQueue mainQueue]
                           completionHandler:^(NSURLResponse *response, NSData *data, NSError *connectionError)
    {
                        json = [NSJSONSerialization JSONObjectWithData:data
                                                                      options:0
                                                                        error:nil];
        // pega os dados do json
        NSNumber *notaId = json[@"notaFiscalId"];
        NSDictionary *parlamentar = json[@"parlamentar"];
        NSDictionary *partido = json[@"partido"];
        NSString *cota = json[@"cota"];
        NSString *unidadeFederativa = json[@"uf"];
        NSString *dataEmissão = json[@"dataEmissao"];
        NSString *descricao = json[@"Viagem para Buenos Aires"];
        NSString *fornecedor = json[@"fornecedor"];
        NSString *cpfCnpj = json[@"cpfcnpj"];
        NSString *ano = json[@"ano"];
        NSString *mes = json[@"mes"];
        NSString *numeroDocumento = json[@"numeroDocumento"];
        NSString *valor = json[@"valor"];
        NSString *valorGlosa = json[@"valorGlosa"];
        NSString *valorLiquido = json[@"valorLiquido"];
        
        
    }];
    
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
