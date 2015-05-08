//
//  ViewController.m
//  fiscalize-ios
//
//  Created by Jhonatan Richard Raphael on 5/8/15.
//  Copyright (c) 2015 OPS. All rights reserved.
//

#import "ViewController.h"
#import <SDWebImage/UIImageView+WebCache.h>

@interface ViewController ()


@property (weak, nonatomic) IBOutlet UILabel *numeroNotaLabel;

@property (weak, nonatomic) IBOutlet UIImageView *fotoPartidoImageView;
@property (weak, nonatomic) IBOutlet UILabel *siglaPartidoLabel;
@property (weak, nonatomic) IBOutlet UILabel *nomePartidoLabel;

@property (weak, nonatomic) IBOutlet UIImageView *fotoParlamentarImageView;
@property (weak, nonatomic) IBOutlet UILabel *nomeParlamentarLabel;
@property (weak, nonatomic) IBOutlet UILabel *emailParlamentarLabel;


@property (weak, nonatomic) IBOutlet UILabel *unidadeFederativaLabel;
@property (weak, nonatomic) IBOutlet UILabel *cpfCnpjLabel;
@property (weak, nonatomic) IBOutlet UILabel *cotaLabel;
@property (weak, nonatomic) IBOutlet UILabel *fornecedorLabel;
@property (weak, nonatomic) IBOutlet UILabel *dataRessarcimentoLabel;
@property (weak, nonatomic) IBOutlet UILabel *dataEmissaoLabel;
@property (weak, nonatomic) IBOutlet UILabel *valorLabel;
@property (weak, nonatomic) IBOutlet UILabel *valorGlosaLabel;
@property (weak, nonatomic) IBOutlet UILabel *valorLiquidoLabel;
@property (weak, nonatomic) IBOutlet UITextView *descricaoTextView;




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
        // carrega o son
        json = [NSJSONSerialization JSONObjectWithData:data options:0 error:nil];
        
        // pega os dados do json
        [self parseJson:json];
        
        
    }];
    
}

-(void)parseJson:(NSDictionary*)json {
    
    NSNumber *notaId = json[@"notaFiscalId"];
    NSDictionary *parlamentar = json[@"parlamentar"];
    NSString *nomeParlamentar = parlamentar[@"nome"];
    NSString *emailParlamentar = parlamentar[@"email"];
    NSString *urlFotoParlamentar = parlamentar[@"urlFoto"];
    NSDictionary *partido = json[@"partido"];
    NSString *nomePartido = partido[@"nome"];
    NSString *siglaPartido = partido[@"sigla"];
    NSString *urlFotoPartido = partido[@"urlFoto"];
    NSString *cota = json[@"cota"];
    NSString *unidadeFederativa = json[@"uf"];
    NSString *dataEmissão = json[@"dataEmissao"];
    NSString *descricao = json[@"descricao"];
    NSString *fornecedor = json[@"fornecedor"];
    NSString *cpfCnpj = json[@"cpfcnpj"];
    NSString *anoRessarcimento = json[@"ano"];
    NSString *mesRessarcimento = json[@"mes"];
    NSString *numeroNota = json[@"numeroDocumento"];
    NSString *valor = json[@"valor"];
    NSString *valorGlosa = json[@"valorGlosa"];
    NSString *valorLiquido = json[@"valorLiquido"];
    
    // atualiza a UI
    
    // nota fiscal
    _numeroNotaLabel.text = numeroNota;
    _cotaLabel.text = cota;
    _fornecedorLabel.text = fornecedor;
    _dataEmissaoLabel.text = dataEmissão;
    _dataRessarcimentoLabel.text = [[NSString alloc] initWithFormat:@"%@/%@", mesRessarcimento, anoRessarcimento];
    _valorLabel.text = valor;
    _valorGlosaLabel.text = valorGlosa;
    _valorLiquidoLabel.text = valorLiquido;
    _descricaoTextView.text = descricao;
    
    // partido
    [_fotoPartidoImageView sd_setImageWithURL:[NSURL URLWithString:urlFotoPartido] placeholderImage:nil];
    _siglaPartidoLabel.text = siglaPartido;
    _nomePartidoLabel.text = nomePartido;
    
    // parlamentar
    [_fotoPartidoImageView sd_setImageWithURL:[NSURL URLWithString:urlFotoParlamentar] placeholderImage:nil];
    _nomeParlamentarLabel.text = nomeParlamentar;
    _emailParlamentarLabel.text = emailParlamentar;
    _unidadeFederativaLabel.text = unidadeFederativa;
    _cpfCnpjLabel.text = cpfCnpj;
    
    
}

@end
